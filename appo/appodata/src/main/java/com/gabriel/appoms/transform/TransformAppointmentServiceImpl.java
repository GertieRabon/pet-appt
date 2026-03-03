package com.gabriel.appoms.transform;
import com.gabriel.appoms.entity.AppointmentData;
import com.gabriel.appoms.model.Appointment;
import org.springframework.stereotype.Service;
@Service
public class TransformAppointmentServiceImpl implements TransformAppointmentService {
	@Override
	public AppointmentData transform(Appointment appointment){
		AppointmentData appointmentData = new AppointmentData();
		appointmentData.setId(appointment.getId());
		appointmentData.setOwnerId(appointment.getOwnerId());
		appointmentData.setOwnerFirstName(appointment.getOwnerFirstName());
		appointmentData.setOwnerLastName(appointment.getOwnerLastName());
		appointmentData.setOwnerEmail(appointment.getOwnerEmail());
		appointmentData.setOwnerPhoneNumber(appointment.getOwnerPhoneNumber());
		appointmentData.setOwnerAddress(appointment.getOwnerAddress());
		appointmentData.setPetId(appointment.getPetId());
		appointmentData.setPetName(appointment.getPetName());
		appointmentData.setPetBreed(appointment.getPetBreed());
		appointmentData.setPetAge(appointment.getPetAge());
		appointmentData.setPetMedicalNotes(appointment.getPetMedicalNotes());
		appointmentData.setGroomerId(appointment.getGroomerId());
		appointmentData.setGroomerName(appointment.getGroomerName());
		appointmentData.setServiceTypeId(appointment.getServiceTypeId());
		appointmentData.setServiceTypeName(appointment.getServiceTypeName());
		appointmentData.setServiceTypeDescription(appointment.getServiceTypeDescription());
		appointmentData.setServicePrice(appointment.getServicePrice());
		appointmentData.setApptStatusId(appointment.getApptStatusId());
		appointmentData.setApptStatusName(appointment.getApptStatusName());
		appointmentData.setApptId(appointment.getApptId());
		appointmentData.setApptDate(appointment.getApptDate());
		appointmentData.setApptNotes(appointment.getApptNotes());
		appointmentData.setOwnerId(appointment.getOwnerId());
		appointmentData.setPetId(appointment.getPetId());
		appointmentData.setGroomerId(appointment.getGroomerId());
		appointmentData.setServiceTypeId(appointment.getServiceTypeId());
		appointmentData.setApptStatusId(appointment.getApptStatusId());
		return appointmentData;
	}
	@Override

	public Appointment transform(AppointmentData appointmentData){;
		Appointment appointment = new Appointment();
		appointment.setId(appointmentData.getId());
		appointment.setOwnerId(appointmentData.getOwnerId());
		appointment.setOwnerFirstName(appointmentData.getOwnerFirstName());
		appointment.setOwnerLastName(appointmentData.getOwnerLastName());
		appointment.setOwnerEmail(appointmentData.getOwnerEmail());
		appointment.setOwnerPhoneNumber(appointmentData.getOwnerPhoneNumber());
		appointment.setOwnerAddress(appointmentData.getOwnerAddress());
		appointment.setPetId(appointmentData.getPetId());
		appointment.setPetName(appointmentData.getPetName());
		appointment.setPetBreed(appointmentData.getPetBreed());
		appointment.setPetAge(appointmentData.getPetAge());
		appointment.setPetMedicalNotes(appointmentData.getPetMedicalNotes());
		appointment.setGroomerId(appointmentData.getGroomerId());
		appointment.setGroomerName(appointmentData.getGroomerName());
		appointment.setServiceTypeId(appointmentData.getServiceTypeId());
		appointment.setServiceTypeName(appointmentData.getServiceTypeName());
		appointment.setServiceTypeDescription(appointmentData.getServiceTypeDescription());
		appointment.setServicePrice(appointmentData.getServicePrice());
		appointment.setApptStatusId(appointmentData.getApptStatusId());
		appointment.setApptStatusName(appointmentData.getApptStatusName());
		appointment.setApptId(appointmentData.getApptId());
		appointment.setApptDate(appointmentData.getApptDate());
		appointment.setApptNotes(appointmentData.getApptNotes());
		appointment.setOwnerId(appointmentData.getOwnerId());
		appointment.setPetId(appointmentData.getPetId());
		appointment.setGroomerId(appointmentData.getGroomerId());
		appointment.setServiceTypeId(appointmentData.getServiceTypeId());
		appointment.setApptStatusId(appointmentData.getApptStatusId());
		appointment.setCreated(appointmentData.getCreated());
		appointment.setLastUpdated(appointmentData.getLastUpdated());
		return appointment;
	}
}
