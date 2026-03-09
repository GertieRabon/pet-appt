package com.gabriel.appoms.kafka;

import com.gabriel.appodata.enums.AppointmentStatus;
import java.time.LocalDateTime;

public class AppointmentEvent {

    private Long appointmentId;
    private Long ownerId;
    private AppointmentStatus status;
    private String eventType; // "CREATED", "APPROVED", "REJECTED", "COMPLETED", "CANCELLED"
    private LocalDateTime timestamp;

    public AppointmentEvent() {}

    public AppointmentEvent(Long appointmentId, Long ownerId,
                            AppointmentStatus status, String eventType) {
        this.appointmentId = appointmentId;
        this.ownerId = ownerId;
        this.status = status;
        this.eventType = eventType;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getAppointmentId() { return appointmentId; }
    public void setAppointmentId(Long appointmentId) { this.appointmentId = appointmentId; }

    public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }

    public AppointmentStatus getStatus() { return status; }
    public void setStatus(AppointmentStatus status) { this.status = status; }

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}