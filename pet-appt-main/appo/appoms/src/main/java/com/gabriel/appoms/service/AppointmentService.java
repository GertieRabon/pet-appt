package com.gabriel.appoms.service;

import com.gabriel.appodata.entity.Appointment;
import com.gabriel.appoms.dto.request.AppointmentRequest;

import java.util.List;
import java.util.Map;

public interface AppointmentService {

    Appointment createAppointment(AppointmentRequest request);

    List<Appointment> getAllAppointments();

    Appointment getAppointment(Long id);

    List<Appointment> getAppointmentsByOwner(Long ownerId);

    Appointment approveAppointment(Long id);

    Appointment rejectAppointment(Long id);

    Appointment completeAppointment(Long id);

    void cancelAppointment(Long id);

    Map<String, Long> getDashboard();
}