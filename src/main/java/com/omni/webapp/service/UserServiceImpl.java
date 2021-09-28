package com.omni.webapp.service;

import com.omni.webapp.models.UserDto;
import com.omni.webapp.models.UserEntity;
import com.omni.webapp.models.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto user) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        userEntity.setEncryptedPassword("test");
        userEntity.setUserId("test");
        userEntity.setUserName("test1");
        userEntity.setEmail("test@test.com");

        UserEntity userDetails = userRepository.save(userEntity);

        UserDto returnUser = new UserDto();
        BeanUtils.copyProperties(userDetails, returnUser);

        return returnUser;
    }
}
