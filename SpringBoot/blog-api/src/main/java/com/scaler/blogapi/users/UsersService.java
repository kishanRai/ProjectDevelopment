package com.scaler.blogapi.users;

import com.scaler.blogapi.security.jwt.JWTService;
import com.scaler.blogapi.users.dtos.CreateUserDTO;
import com.scaler.blogapi.users.dtos.LoginUserDTO;
import com.scaler.blogapi.users.dtos.UserResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private final UsersRespository usersRespository;
    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public UsersService(@Autowired UsersRespository usersRespository,
                        @Autowired ModelMapper modelMapper,
                        @Autowired PasswordEncoder passwordEncoder, JWTService jwtService) {
        this.usersRespository = usersRespository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public UserResponseDTO createUser(CreateUserDTO createUserDTO) {

        // TODO : Validate email
        // TODO : Check if username already exisits
        var newUserEntity = modelMapper.map(createUserDTO, UserEntity.class);
        newUserEntity.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        var savedUser = usersRespository.save(newUserEntity);
        var userResponseDTO = modelMapper.map(savedUser, UserResponseDTO.class);
        userResponseDTO.setToken(jwtService.createJWT(savedUser.getId()));
        return userResponseDTO;
    }

    public UserResponseDTO loginUser(LoginUserDTO loginUserDTO) {

        var userEntity = usersRespository.findByUsername(loginUserDTO.getUsername());
        if (null == userEntity) {
            throw new UserNotFoundException(loginUserDTO.getUsername());
        }

        var passwordMatch = passwordEncoder.matches(loginUserDTO.getPassword(), userEntity.getPassword());

        if (!passwordMatch) {
            throw new IllegalArgumentException("Incorrect Password");
        }

        var userResponseDTO = modelMapper.map(userEntity, UserResponseDTO.class);
        userResponseDTO.setToken(jwtService.createJWT(userEntity.getId()));

        return userResponseDTO;
    }

    public static class UserNotFoundException extends IllegalArgumentException {
        public UserNotFoundException(Long id) {
            super("User with id" + id + "not found");
        }

        public UserNotFoundException(String username) {
            super("User with username:" + username + "not found");
        }
    }
}
