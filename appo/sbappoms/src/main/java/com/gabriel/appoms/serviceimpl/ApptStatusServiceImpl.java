package com.gabriel.appoms.serviceimpl;
import com.gabriel.appoms.entity.ApptStatusData;
import com.gabriel.appoms.model.ApptStatus;
import com.gabriel.appoms.repository.ApptStatusDataRepository;
import com.gabriel.appoms.service.ApptStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class ApptStatusServiceImpl implements ApptStatusService {
	Logger logger = LoggerFactory.getLogger(ApptStatusServiceImpl.class);
	@Autowired
	ApptStatusDataRepository apptStatusDataRepository;
	@Autowired
	@Override
	public ApptStatus[] getAll() {
		List<ApptStatusData> apptStatussData = new ArrayList<>();
		List<ApptStatus> apptStatuss = new ArrayList<>();
		apptStatusDataRepository.findAll().forEach(apptStatussData::add);
		Iterator<ApptStatusData> it = apptStatussData.iterator();
		while(it.hasNext()) {
			ApptStatusData apptStatusData = it.next();
			ApptStatus apptStatus = new ApptStatus();
			apptStatus.setId(apptStatusData.getId());
			apptStatus.setName(apptStatusData.getName());
			apptStatuss.add(apptStatus);
		}
		ApptStatus[] array = new ApptStatus[apptStatuss.size()];
		for  (int i=0; i<apptStatuss.size(); i++){
			array[i] = apptStatuss.get(i);
		}
		return array;
	}
	@Override
	public ApptStatus create(ApptStatus apptStatus) {
		logger.info(" add:Input " + apptStatus.toString());
		ApptStatusData apptStatusData = new ApptStatusData();
		apptStatusData.setName(apptStatus.getName());
		apptStatusData = apptStatusDataRepository.save(apptStatusData);
		logger.info(" add:Input " + apptStatusData.toString());
			ApptStatus newApptStatus = new ApptStatus();
			newApptStatus.setId(apptStatusData.getId());
			newApptStatus.setName(apptStatusData.getName());
		return newApptStatus;
	}

	@Override
	public ApptStatus update(ApptStatus apptStatus) {
		ApptStatus updatedApptStatus = null;
		int id = apptStatus.getId();
		Optional<ApptStatusData> optional  = apptStatusDataRepository.findById(apptStatus.getId());
		if(optional.isPresent()){
			ApptStatusData originalApptStatusData = new ApptStatusData();
			originalApptStatusData.setId(apptStatus.getId());
			originalApptStatusData.setName(apptStatus.getName());
			originalApptStatusData.setCreated(optional.get().getCreated());
			ApptStatusData apptStatusData = apptStatusDataRepository.save(originalApptStatusData);
			updatedApptStatus = new ApptStatus();
			updatedApptStatus.setId(apptStatusData.getId());
			updatedApptStatus.setName(apptStatusData.getName());
			updatedApptStatus.setCreated(apptStatusData.getCreated());
			updatedApptStatus.setLastUpdated(apptStatusData.getLastUpdated());
		}
		else {
			logger.error("ApptStatus record with id: " + Integer.toString(id) + " do not exist ");

		}
		return updatedApptStatus;
	}

	@Override
	public ApptStatus get(Integer id) {
		logger.info(" Input id >> "+  Integer.toString(id) );
		ApptStatus apptStatus = null;
		Optional<ApptStatusData> optional = apptStatusDataRepository.findById(id);
		if(optional.isPresent()) {
			logger.info(" Is present >> ");
			apptStatus = new ApptStatus();
			apptStatus.setId(optional.get().getId());
			apptStatus.setName(optional.get().getName());
			apptStatus.setCreated(optional.get().getCreated());
			apptStatus.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.info(" Failed >> unable to locate id: " +  Integer.toString(id)  );
		}
		return apptStatus;
	}
	@Override
	public void delete(Integer id) {
		ApptStatus apptStatus = null;
		logger.info(" Input >> " +  Integer.toString(id));
		Optional<ApptStatusData> optional = apptStatusDataRepository.findById(id);
		if( optional.isPresent()) {
			ApptStatusData apptStatusDatum = optional.get();
			apptStatusDataRepository.delete(optional.get());
			logger.info(" Successfully deleted ApptStatus record with id: " + Integer.toString(id));
			apptStatus = new ApptStatus();
			apptStatus.setId(optional.get().getId());
			apptStatus.setName(optional.get().getName());
			apptStatus.setCreated(optional.get().getCreated());
			apptStatus.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.error(" Unable to locate apptStatus with id:" +  Integer.toString(id));
		}
	}
}
