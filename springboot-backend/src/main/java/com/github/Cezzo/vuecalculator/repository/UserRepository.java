package com.github.Cezzo.vuecalculator.repository;

import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import com.github.Cezzo.vuecalculator.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
