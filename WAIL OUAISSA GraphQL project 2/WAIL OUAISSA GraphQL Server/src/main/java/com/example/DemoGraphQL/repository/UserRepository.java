package com.example.DemoGraphQL.repository;

import com.example.DemoGraphQL.model.User;
import com.example.DemoGraphQL.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
}
