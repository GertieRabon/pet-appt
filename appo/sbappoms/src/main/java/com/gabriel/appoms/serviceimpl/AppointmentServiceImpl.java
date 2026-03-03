package com.gabriel.appoms.serviceimpl;
import com.gabriel.appoms.entity.AppointmentData;
import com.gabriel.appoms.model.Appointment;
import com.gabriel.appoms.repository.AppointmentDataRepository;
import com.gabriel.appoms.service.AppointmentService;
import com.gabriel.appoms.transform.TransformAppointmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class AppointmentServiceImpl implements AppointmentService {
	Logger logger = LoggerFactory.getLogger(AppointmentServiceImpl.class);
	@Autowired
	AppointmentDataRepository appointmentDataRepository;
	@Autowired
	TransformAppointmentService tansformAppointmentService;
	@Override
	public Appointment[] getAll() {
		List<AppointmentData> appointmentsData = new ArrayList<>();
		List<Appointment> appointments = new ArrayList<>();
		appointmentDataRepository.findAll().forEach(appointmentsData::add);
		Iterator<AppointmentData> it = appointmentsData.iterator();
		while(it.hasNext()) {
			AppointmentData appointmentData = it.next();
			Appointment appointment = tansformAppointmentService.transform(appointmentData);
			appointments.add(appointment);
		}
		Appointment[] array = new Appointment[appointments.size()];
		for  (int i=0; i<appointments.size(); i++){
			array[i] = appointments.get(i);
		}
		return array;
	}
	@Override
	public Appointment create(Appointment appointment) {
		logger.info(" add:Input " + appointment.toString());
		AppointmentData appointmentData = tansformAppointmentService.transform(appointment);
		appointmentData = appointmentDataRepository.save(appointmentData);
		logger.info(" add:Input " + appointmentData.toString());
			Appointment newAppointment = tansformAppointmentService.transform(appointmentData);
		return newAppointment;
	}

	@Override
	public Appointment update(Appointment appointment) {
		Appointment updatedAppointment = null;
		int id = appointment.getId();
		Optional<AppointmentData> optional  = appointmentDataRepository.findById(appointment.getId());
		if(optional.isPresent()){
			AppointmentData originalAppointmentData = tansformAppointmentService.transform(appointment);
			originalAppointmentData.setCreated(optional.get().getCreated());
			AppointmentData appointmentData = appointmentDataRepository.save(originalAppointmentData);
			updatedAppointment = tansformAppointmentService.transform(appointmentData);
		}
		else {
			logger.error("Appointment record with id: " + Integer.toString(id) + " do not exist ");

		}
		return updatedAppointment;
	}

	@Override
	public Appointment get(Integer id) {
		logger.info(" Input id >> "+  Integer.toString(id) );
		Appointment appointment = null;
		Optional<AppointmentData> optional = appointmentDataRepository.findById(id);
		if(optional.isPresent()) {
			logger.info(" Is present >> ");
			appointment = tansformAppointmentService.transform(optional.get());
		}
		else {
			logger.info(" Failed >> unable to locate id: " +  Integer.toString(id)  );
		}
		return appointment;
	}
	@Override
	public void delete(Integer id) {
		Appointment appointment = null;
		logger.info(" Input >> " +  Integer.toString(id));
		Optional<AppointmentData> optional = appointmentDataRepository.findById(id);
		if( optional.isPresent()) {
			AppointmentData appointmentDatum = optional.get();
			appointmentDataRepository.delete(optional.get());
			logger.info(" Successfully deleted Appointment record with id: " + Integer.toString(id));
			appointment = tansformAppointmentService.transform(optional.get());
		}
		else {
			logger.error(" Unable to locate appointment with id:" +  Integer.toString(id));
		}
	}
}
