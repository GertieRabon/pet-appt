package com.gabriel.appoms.serviceimpl;

import com.gabriel.appodata.entity.Appointment;
import com.gabriel.appodata.entity.ServiceType;
import com.gabriel.appodata.enums.AppointmentStatus;
import com.gabriel.appodata.repository.AppointmentRepository;
import com.gabriel.appodata.repository.ServiceRepository;
import com.gabriel.appoms.service.AdminService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    private final AppointmentRepository appointmentRepository;
    private final ServiceRepository serviceRepository;

    public AdminServiceImpl(AppointmentRepository appointmentRepository,
                            ServiceRepository serviceRepository) {
        this.appointmentRepository = appointmentRepository;
        this.serviceRepository = serviceRepository;
    }

    @Override
    public Map<String, Long> getDashboardStats() {

        Map<String, Long> stats = new HashMap<>();

        stats.put("pendingBookings",
                appointmentRepository.countByStatus(AppointmentStatus.PENDING));

        stats.put("approvedBookings",
                appointmentRepository.countByStatus(AppointmentStatus.APPROVED));

        stats.put("completedBookings",
                appointmentRepository.countByStatus(AppointmentStatus.COMPLETED));

        stats.put("cancelledBookings",
                appointmentRepository.countByStatus(AppointmentStatus.CANCELLED));

        stats.put("rejectedBookings",
                appointmentRepository.countByStatus(AppointmentStatus.REJECTED));

        return stats;
    }

    @Override
    public List<Appointment> getAllBookings() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> filterByStatus(AppointmentStatus status) {
        return appointmentRepository.findByStatus(status);
    }

    @Override
    public List<Appointment> filterByDate(LocalDateTime start, LocalDateTime end) {
        return appointmentRepository.findByAppointmentDateBetween(start, end);
    }

    @Override
    public Appointment approveBooking(Long id) {

        Appointment appointment = appointmentRepository.findById(id).orElseThrow();

        appointment.setStatus(AppointmentStatus.APPROVED);

        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment rejectBooking(Long id) {

        Appointment appointment = appointmentRepository.findById(id).orElseThrow();

        appointment.setStatus(AppointmentStatus.REJECTED);

        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment assignGroomer(Long appointmentId, Integer groomerId) {

        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();

        appointment.setGroomerId(groomerId);

        return appointmentRepository.save(appointment);
    }

    @Override
    public Double getRevenue() {

        List<Appointment> completedAppointments =
                appointmentRepository.findByStatus(AppointmentStatus.COMPLETED);

        double revenue = 0;

        for (Appointment appt : completedAppointments) {

            ServiceType service =
                    serviceRepository.findById(appt.getServiceId()).orElseThrow();

            revenue += service.getPrice();
        }

        return revenue;
    }
}