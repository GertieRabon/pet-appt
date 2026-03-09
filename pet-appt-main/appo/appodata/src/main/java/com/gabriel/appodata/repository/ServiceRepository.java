package com.gabriel.appodata.repository;

import com.gabriel.appodata.entity.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ServiceType, Integer> {
}