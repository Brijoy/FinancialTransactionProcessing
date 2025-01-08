package com.td.notification_service.service;

import com.td.notification_service.dto.UserDTO;

public interface UserService {
    public UserDTO getUserById(Long id);
    public UserDTO createUser(UserDTO userDTO);
    public UserDTO updateUser(UserDTO userDTO);
    public void deleteUser(Long id);
}
