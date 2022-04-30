package com.example.authorizationmicroservice.repositories;

import com.example.authorizationmicroservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail (String email);
}
