package com.ynov.wsproject1.service;

import com.ynov.wsproject1.DTO.CreateUserRequest;
import com.ynov.wsproject1.DTO.CreateUserResponse;
import com.ynov.wsproject1.DTO.UserUpdateRequest;
import com.ynov.wsproject1.model.UserFull;
import com.ynov.wsproject1.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.ynov.wsproject1.repository.UserRepository;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private AtomicLong locationIdGenerator = new AtomicLong(1);



    public Page<UserFull> getUsersSortedByRegistrationDate(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAllByOrderByRegisterDateDesc(pageable);
    }

    @Override
    public UserFull getUserById(String userId) {
        return userRepository.findById(userId).orElse(null);
    }


    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {

        UserFull newUser = new UserFull();
        newUser.setId(UUID.randomUUID().toString());
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setEmail(request.getEmail());



        UserFull savedUser = userRepository.save(newUser);


        CreateUserResponse response = new CreateUserResponse();
        response.setId(savedUser.getId());
        response.setFirstName(savedUser.getFirstName());
        response.setLastName(savedUser.getLastName());
        response.setEmail(savedUser.getEmail());


        return response;
    }

    public UserFull updateUser(UserFull existingUser, UserUpdateRequest userUpdateRequest) {
        if (userUpdateRequest.getTitle() != null) {
            existingUser.setTitle(userUpdateRequest.getTitle());
        }
        if (userUpdateRequest.getFirstName() != null) {
            existingUser.setFirstName(userUpdateRequest.getFirstName());
        }
        if (userUpdateRequest.getLastName() != null) {
            existingUser.setLastName(userUpdateRequest.getLastName());
        }
        if (userUpdateRequest.getGender() != null) {
            existingUser.setGender(userUpdateRequest.getGender());
        }
        if (userUpdateRequest.getDateOfBirth() != null) {
            existingUser.setDateOfBirth(userUpdateRequest.getDateOfBirth());
        }
        if (userUpdateRequest.getPhone() != null) {
            existingUser.setPhone(userUpdateRequest.getPhone());
        }
        if (userUpdateRequest.getPicture() != null) {
            existingUser.setPicture(userUpdateRequest.getPicture());
        }



         return userRepository.save(existingUser);
    }





    @Override
    public String deleteUser(String userId) {
         if (!userRepository.existsById(userId)) {
             return "User with ID " + userId + " not found. User not deleted.";
        }

         userRepository.deleteById(userId);
         return "User with ID " + userId + " deleted successfully.";
    }
}
