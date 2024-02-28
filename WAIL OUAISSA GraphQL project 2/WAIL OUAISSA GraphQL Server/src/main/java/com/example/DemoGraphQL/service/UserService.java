package com.example.DemoGraphQL.service;

import com.example.DemoGraphQL.exception.UserNotFoundException;
import com.example.DemoGraphQL.model.User;
import com.example.DemoGraphQL.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired

    private CommentService commentService;


    public Iterable<User> getUsers(Integer page, Integer limit, String sortBy) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortBy).ascending());
        return userRepository.findAll(pageable).getContent();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    @Transactional
    public User createUser(String firstName, String lastName, String email) {
        User newUser = new User(firstName, lastName, email);
        return userRepository.save(newUser);
    }

    @Transactional
    public User updateUser(Long id, String firstName, String lastName) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        existingUser.setFirstName(firstName);
        existingUser.setLastName(lastName);
        return userRepository.save(existingUser);
    }

    @Transactional
    public String deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        userRepository.delete(user);
        return id.toString();
    }

    public void deleteComment(Long id) {
         commentService.deleteCommentById(id);
    }

    @Transactional(readOnly = true)
    public int getTotalUserCount() {
        return (int) userRepository.count();
    }

    public List<User> getUsers(int page, int limit, String sortBy) {
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(sortBy));
        Page<User> pageResult = userRepository.findAll(pageRequest);
        return pageResult.getContent();
    }

    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
    public Page<User> getUsersPageable(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);

    }

    public Optional<User> findUserById(Long userId) {
        return userRepository.findById(userId);
    }
}

