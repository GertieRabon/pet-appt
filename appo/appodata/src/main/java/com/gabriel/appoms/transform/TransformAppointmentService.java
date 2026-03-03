package com.gabriel.appoms.transform;
import com.gabriel.appoms.entity.AppointmentData;
import com.gabriel.appoms.model.Appointment;
public interface TransformAppointmentService {
	AppointmentData transform(Appointment appointment);
	Appointment transform(AppointmentData appointmentData);
}
