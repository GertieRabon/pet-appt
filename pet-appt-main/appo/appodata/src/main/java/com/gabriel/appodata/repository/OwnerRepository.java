package com.gabriel.appodata.repository;

import com.gabriel.appodata.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}