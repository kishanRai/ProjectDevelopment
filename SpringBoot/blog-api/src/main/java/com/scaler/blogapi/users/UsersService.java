package com.scaler.blogapi.users;

import com.scaler.blogapi.users.dtos.CreateUserDTO;
import com.scaler.blogapi.users.dtos.LoginUserDTO;
import com.scaler.blogapi.users.dtos.UserResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private final UsersRespository usersRespository;
    private final ModelMapper modelMapper;

    public UsersService(UsersRespository usersRespository, ModelMapper modelMapper) {
        this.usersRespository = usersRespository;
        this.modelMapper = modelMapper;
    }

    public UserResponseDTO createUser(CreateUserDTO createUserDTO) {
        // TODO : Encrpt Password
        // TODO : Validate email
        // TODO : Check if username already exisits
        var newUserEntity = modelMapper.map(createUserDTO, UserEntity.class);
        var savedUser = usersRespository.save(newUserEntity);
        return modelMapper.map(savedUser, UserResponseDTO.class);
    }

    public UserResponseDTO loginUser(LoginUserDTO loginUserDTO) {

        var userEntity = usersRespository.findByUsername(loginUserDTO.getUsername());
        if (null == userEntity) {
            throw new UserNotFoundException(loginUserDTO.getUsername());
        }
        // TODO : Encrypt password matching

        if (userEntity.getPassword().equals(loginUserDTO.getPassword())) {
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
