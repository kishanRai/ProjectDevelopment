package com.scaler.blogapi.users;

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

    public UsersService(@Autowired UsersRespository usersRespository,
                        @Autowired ModelMapper modelMapper,
                        @Autowired PasswordEncoder passwordEncoder) {
        this.usersRespository = usersRespository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO createUser(CreateUserDTO createUserDTO) {

        // TODO : Validate email
        // TODO : Check if username already exisits
        var newUserEntity = modelMapper.map(createUserDTO, UserEntity.class);
        newUserEntity.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        var savedUser = usersRespository.save(newUserEntity);
        return modelMapper.map(savedUser, UserResponseDTO.class);
    }

    public UserResponseDTO loginUser(LoginUserDTO loginUserDTO) {

        var userEntity = usersRespository.findByUsername(loginUserDTO.getUsername());
        if (null == userEntity) {
            throw new UserNotFoundException(loginUserDTO.getUsername());
        }
        // TODO : Encrypt password matching
        var passwordMatch = passwordEncoder.matches(loginUserDTO.getPassword(), userEntity.getPassword());

        if (!passwordMatch) {
            throw new IllegalArgumentException("Incorrect Password");
        }

        return modelMapper.map(userEntity, UserResponseDTO.class);
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
