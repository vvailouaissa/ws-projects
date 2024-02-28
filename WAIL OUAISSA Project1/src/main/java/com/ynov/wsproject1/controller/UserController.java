package com.ynov.wsproject1.controller;

import com.ynov.wsproject1.DTO.CreateUserRequest;
import com.ynov.wsproject1.DTO.CreateUserResponse;
import com.ynov.wsproject1.DTO.UserUpdateRequest;
import com.ynov.wsproject1.exception.ResourceNotFoundException;
import com.ynov.wsproject1.model.UserFull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ynov.wsproject1.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users")
    public Page<UserFull> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return userService.getUsersSortedByRegistrationDate(page, size);
    }

    @GetMapping("/users/{userId}")
    public UserFull getUserById(@PathVariable String userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/users")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest userCreateRequest) {
        if (userCreateRequest.getFirstName() == null || userCreateRequest.getLastName() == null ||
                userCreateRequest.getEmail() == null) {
            return ResponseEntity.badRequest().build();
        }

        CreateUserResponse createdUser = userService.createUser(userCreateRequest);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserFull> updateUser(@PathVariable("userId") String userId,
                                               @RequestBody UserUpdateRequest userUpdateRequest) {
        try {
            UserFull existingUser = userService.getUserById(userId);
            if (existingUser == null) {
                return ResponseEntity.notFound().build();
            }

            BeanUtils.copyProperties(userUpdateRequest, existingUser, getNullPropertyNames(userUpdateRequest));
            UserFull updatedUser = userService.updateUser(existingUser,userUpdateRequest);
            return ResponseEntity.ok(updatedUser);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") String userId) {
        String result = userService.deleteUser(userId);
        if (result.contains("not found")) {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
