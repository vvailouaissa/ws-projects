package com.ynov.wsproject1.service;

import com.ynov.wsproject1.DTO.CreateUserRequest;
import com.ynov.wsproject1.DTO.CreateUserResponse;
import com.ynov.wsproject1.DTO.UserUpdateRequest;
import com.ynov.wsproject1.model.UserFull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    public Page<UserFull> getUsersSortedByRegistrationDate(int page, int size);

    UserFull getUserById(String userId);

    CreateUserResponse createUser(CreateUserRequest request);

    UserFull updateUser(UserFull existingUser, UserUpdateRequest userUpdateRequest);


    String deleteUser(String userId);

}
