package com.scaler.blogapi.users;

import com.scaler.blogapi.security.jwt.JWTService;
import com.scaler.blogapi.users.dtos.CreateUserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @SpringBootTest - runs slower when projects get bigger
 */

@DataJpaTest
class UsersServiceTest {

    @Autowired
    private UsersRespository usersRespository;

    /**
     * As we are not using @SpringBootTest annotation instead we are using @DataJpaTest which creates layer for repository layer.
     * So we cannot use @Autowired here
     */
    private UsersService usersService;

    private UsersService getUsersService() {
        if (null == usersService) {
            var modelMapper = new ModelMapper();
            var passwordEncoder = new BCryptPasswordEncoder();
            var jwtService = new JWTService();
            usersService = new UsersService(usersRespository, modelMapper, passwordEncoder, jwtService);
        }
        return usersService;
    }

    @Test
    void createUser_test() {
        var userDTO = new CreateUserDTO();
        userDTO.setEmail("kisha@gmail.com");
        userDTO.setPassword("123456");
        userDTO.setUsername("kishan");
        var savedUser = getUsersService().createUser(userDTO);
        Assertions.assertNotNull(savedUser);
    }
}