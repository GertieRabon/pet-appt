package com.gabriel.appoms.dto.request;

import java.time.LocalDateTime;

public class AppointmentRequest {

    public Long ownerId;

    public Long petId;

    public Integer groomerId;

    public Integer serviceId;

    public LocalDateTime appointmentDate;

    public String notes;

}