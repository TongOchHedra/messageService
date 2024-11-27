package com.example.messageservice.DAO;

import com.example.messageservice.Models.Role;
import com.example.messageservice.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    List<User> findAllByRole(Role role);
}