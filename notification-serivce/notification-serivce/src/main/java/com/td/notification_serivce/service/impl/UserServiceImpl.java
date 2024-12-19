package com.td.notification_serivce.service.impl;

import com.td.notification_serivce.dto.UserDTO;
import com.td.notification_serivce.entity.UserEntity;
import com.td.notification_serivce.mapper.UserMapper;
import com.td.notification_serivce.repository.UserRepository;
import com.td.notification_serivce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDTO getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        return UserMapper.toDTO(userEntity);

    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        UserEntity userEntity = UserMapper.toEntity(userDTO);
        UserEntity savedUser = userRepository.save(userEntity);
        return UserMapper.toDTO(savedUser);

    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        UserEntity existingUserEntity = userRepository.findById(userDTO.getId()).orElse(null);
        if (existingUserEntity != null) {
            existingUserEntity = UserMapper.toEntity(userDTO);
            UserEntity savedUser = userRepository.save(existingUserEntity);
            return UserMapper.toDTO(savedUser);
        } else {
            return null;
        }

    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);


    }
}
