package com.example.vaccineapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.vaccineapp.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
