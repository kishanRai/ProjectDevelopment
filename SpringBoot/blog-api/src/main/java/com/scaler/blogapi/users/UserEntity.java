package com.scaler.blogapi.users;

import com.scaler.blogapi.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "users")
public class UserEntity extends BaseEntity {

    @Column(unique = true, nullable = false, length = 50)
    String username;


    String password; // TODO : Hash password

    String bio;

    String image;


}
