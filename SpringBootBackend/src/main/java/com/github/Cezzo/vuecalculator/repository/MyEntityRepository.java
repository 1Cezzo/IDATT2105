package com.github.Cezzo.vuecalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.github.Cezzo.vuecalculator.model.MyEntity;

public interface MyEntityRepository extends JpaRepository<MyEntity, Long> {
}
