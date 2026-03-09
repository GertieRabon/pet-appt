package com.gabriel.appoms.service;

import com.gabriel.appodata.entity.Appointment;
import com.gabriel.appodata.enums.AppointmentStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface AdminService {

    Map<String, Long> getDashboardStats();

    List<Appointment> getAllBookings();

    List<Appointment> filterByStatus(AppointmentStatus status);

    List<Appointment> filterByDate(LocalDateTime start, LocalDateTime end);

    Appointment approveBooking(Long id);

    Appointment rejectBooking(Long id);

    Appointment assignGroomer(Long appointmentId, Integer groomerId);

    Double getRevenue();
}