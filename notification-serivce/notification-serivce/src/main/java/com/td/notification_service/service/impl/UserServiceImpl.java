package com.td.notification_service.service.impl;

import com.td.notification_service.dto.UserDTO;
import com.td.notification_service.entity.UserEntity;
import com.td.notification_service.exception.ResourceNotFoundException;
import com.td.notification_service.mapper.UserMapper;
import com.td.notification_service.repository.UserRepository;
import com.td.notification_service.service.UserService;
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
        UserEntity existingUserEntity = userRepository.findById(userDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("User","id",userDTO.getId()));
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

        UserEntity existingUserEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User","id",id));
        userRepository.deleteById(id);


    }
}
