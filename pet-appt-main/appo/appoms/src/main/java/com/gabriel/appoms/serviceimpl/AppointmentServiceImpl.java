package com.gabriel.appoms.serviceimpl;

import com.gabriel.appodata.entity.Appointment;
import com.gabriel.appodata.enums.AppointmentStatus;
import com.gabriel.appodata.repository.AppointmentRepository;
import com.gabriel.appoms.dto.request.AppointmentRequest;
import com.gabriel.appoms.kafka.AppointmentEvent;
import com.gabriel.appoms.kafka.AppointmentProducer;
import com.gabriel.appoms.service.AppointmentService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentProducer appointmentProducer;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
                                  AppointmentProducer appointmentProducer) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentProducer = appointmentProducer;
    }

    @Override
    public Appointment createAppointment(AppointmentRequest request) {

        Appointment appointment = new Appointment();

        appointment.setOwnerId(request.ownerId);
        appointment.setPetId(request.petId);
        appointment.setGroomerId(request.groomerId);
        appointment.setServiceId(request.serviceId);
        appointment.setAppointmentDate(request.appointmentDate);
        appointment.setNotes(request.notes);

        appointment.setStatus(AppointmentStatus.PENDING);

        Appointment saved = appointmentRepository.save(appointment);

        appointmentProducer.sendAppointmentEvent(
                new AppointmentEvent(saved.getId(), saved.getOwnerId(), saved.getStatus(), "CREATED")
        );

        return saved;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment getAppointment(Long id) {
        return appointmentRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Appointment> getAppointmentsByOwner(Long ownerId) {
        return appointmentRepository.findByOwnerId(ownerId);
    }

    @Override
    public Appointment approveAppointment(Long id) {

        Appointment appt = appointmentRepository.findById(id).orElseThrow();

        appt.setStatus(AppointmentStatus.APPROVED);

        Appointment saved = appointmentRepository.save(appt);

        appointmentProducer.sendAppointmentEvent(
                new AppointmentEvent(saved.getId(), saved.getOwnerId(), saved.getStatus(), "APPROVED")
        );

        return saved;
    }

    @Override
    public Appointment rejectAppointment(Long id) {

        Appointment appt = appointmentRepository.findById(id).orElseThrow();

        appt.setStatus(AppointmentStatus.REJECTED);

        Appointment saved = appointmentRepository.save(appt);

        appointmentProducer.sendAppointmentEvent(
                new AppointmentEvent(saved.getId(), saved.getOwnerId(), saved.getStatus(), "REJECTED")
        );

        return saved;
    }

    @Override
    public Appointment completeAppointment(Long id) {

        Appointment appt = appointmentRepository.findById(id).orElseThrow();

        appt.setStatus(AppointmentStatus.COMPLETED);

        Appointment saved = appointmentRepository.save(appt);

        appointmentProducer.sendAppointmentEvent(
                new AppointmentEvent(saved.getId(), saved.getOwnerId(), saved.getStatus(), "COMPLETED")
        );

        return saved;
    }

    @Override
    public void cancelAppointment(Long id) {

        Appointment appointment = appointmentRepository.findById(id).orElseThrow();

        appointment.setStatus(AppointmentStatus.CANCELLED);

        Appointment saved = appointmentRepository.save(appointment);

        appointmentProducer.sendAppointmentEvent(
                new AppointmentEvent(saved.getId(), saved.getOwnerId(), saved.getStatus(), "CANCELLED")
        );
    }

    @Override
    public Map<String, Long> getDashboard() {

        Map<String, Long> stats = new HashMap<>();

        stats.put("pending", appointmentRepository.countByStatus(AppointmentStatus.PENDING));
        stats.put("completed", appointmentRepository.countByStatus(AppointmentStatus.COMPLETED));
        stats.put("cancelled", appointmentRepository.countByStatus(AppointmentStatus.CANCELLED));

        return stats;
    }
}