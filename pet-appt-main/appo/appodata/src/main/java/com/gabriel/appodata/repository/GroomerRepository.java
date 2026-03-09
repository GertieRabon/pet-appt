package com.gabriel.appodata.repository;

import com.gabriel.appodata.entity.Groomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroomerRepository extends JpaRepository<Groomer, Integer> {
}