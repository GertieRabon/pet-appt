package com.gabriel.appoms.serviceimpl;
import com.gabriel.appoms.entity.ApptData;
import com.gabriel.appoms.model.Appt;
import com.gabriel.appoms.repository.ApptDataRepository;
import com.gabriel.appoms.service.ApptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class ApptServiceImpl implements ApptService {
	Logger logger = LoggerFactory.getLogger(ApptServiceImpl.class);
	@Autowired
	ApptDataRepository apptDataRepository;
	@Autowired
	@Override
	public Appt[] getAll() {
		List<ApptData> apptsData = new ArrayList<>();
		List<Appt> appts = new ArrayList<>();
		apptDataRepository.findAll().forEach(apptsData::add);
		Iterator<ApptData> it = apptsData.iterator();
		while(it.hasNext()) {
			ApptData apptData = it.next();
			Appt appt = new Appt();
			appt.setId(apptData.getId());
			appt.setName(apptData.getName());
			appts.add(appt);
		}
		Appt[] array = new Appt[appts.size()];
		for  (int i=0; i<appts.size(); i++){
			array[i] = appts.get(i);
		}
		return array;
	}
	@Override
	public Appt create(Appt appt) {
		logger.info(" add:Input " + appt.toString());
		ApptData apptData = new ApptData();
		apptData.setName(appt.getName());
		apptData = apptDataRepository.save(apptData);
		logger.info(" add:Input " + apptData.toString());
			Appt newAppt = new Appt();
			newAppt.setId(apptData.getId());
			newAppt.setName(apptData.getName());
		return newAppt;
	}

	@Override
	public Appt update(Appt appt) {
		Appt updatedAppt = null;
		int id = appt.getId();
		Optional<ApptData> optional  = apptDataRepository.findById(appt.getId());
		if(optional.isPresent()){
			ApptData originalApptData = new ApptData();
			originalApptData.setId(appt.getId());
			originalApptData.setName(appt.getName());
			originalApptData.setCreated(optional.get().getCreated());
			ApptData apptData = apptDataRepository.save(originalApptData);
			updatedAppt = new Appt();
			updatedAppt.setId(apptData.getId());
			updatedAppt.setName(apptData.getName());
			updatedAppt.setCreated(apptData.getCreated());
			updatedAppt.setLastUpdated(apptData.getLastUpdated());
		}
		else {
			logger.error("Appt record with id: " + Integer.toString(id) + " do not exist ");

		}
		return updatedAppt;
	}

	@Override
	public Appt get(Integer id) {
		logger.info(" Input id >> "+  Integer.toString(id) );
		Appt appt = null;
		Optional<ApptData> optional = apptDataRepository.findById(id);
		if(optional.isPresent()) {
			logger.info(" Is present >> ");
			appt = new Appt();
			appt.setId(optional.get().getId());
			appt.setName(optional.get().getName());
			appt.setCreated(optional.get().getCreated());
			appt.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.info(" Failed >> unable to locate id: " +  Integer.toString(id)  );
		}
		return appt;
	}
	@Override
	public void delete(Integer id) {
		Appt appt = null;
		logger.info(" Input >> " +  Integer.toString(id));
		Optional<ApptData> optional = apptDataRepository.findById(id);
		if( optional.isPresent()) {
			ApptData apptDatum = optional.get();
			apptDataRepository.delete(optional.get());
			logger.info(" Successfully deleted Appt record with id: " + Integer.toString(id));
			appt = new Appt();
			appt.setId(optional.get().getId());
			appt.setName(optional.get().getName());
			appt.setCreated(optional.get().getCreated());
			appt.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.error(" Unable to locate appt with id:" +  Integer.toString(id));
		}
	}
}
