package com.ynov.wsproject1.repository;

import com.ynov.wsproject1.model.UserFull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserFull, String> {

    @Query("SELECT u FROM UserFull u ORDER BY u.registerDate")
    List<UserFull> findUsersWithPagination(int offset, int limit);


    Page<UserFull> findAllByOrderByRegisterDateDesc(Pageable pageable);

}
