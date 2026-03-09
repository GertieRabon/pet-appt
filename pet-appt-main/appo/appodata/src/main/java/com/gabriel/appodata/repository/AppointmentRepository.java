package com.gabriel.appodata.repository;

import com.gabriel.appodata.entity.Appointment;
import com.gabriel.appodata.enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByOwnerId(Long ownerId);

    List<Appointment> findByPetId(Long petId);

    List<Appointment> findByStatus(AppointmentStatus status);

    List<Appointment> findByAppointmentDateBetween(LocalDateTime start, LocalDateTime end);

    long countByStatus(AppointmentStatus status);

}