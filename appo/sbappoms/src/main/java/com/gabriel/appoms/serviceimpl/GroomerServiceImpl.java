package com.gabriel.appoms.serviceimpl;
import com.gabriel.appoms.entity.GroomerData;
import com.gabriel.appoms.model.Groomer;
import com.gabriel.appoms.repository.GroomerDataRepository;
import com.gabriel.appoms.service.GroomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class GroomerServiceImpl implements GroomerService {
	Logger logger = LoggerFactory.getLogger(GroomerServiceImpl.class);
	@Autowired
	GroomerDataRepository groomerDataRepository;
	@Autowired
	@Override
	public Groomer[] getAll() {
		List<GroomerData> groomersData = new ArrayList<>();
		List<Groomer> groomers = new ArrayList<>();
		groomerDataRepository.findAll().forEach(groomersData::add);
		Iterator<GroomerData> it = groomersData.iterator();
		while(it.hasNext()) {
			GroomerData groomerData = it.next();
			Groomer groomer = new Groomer();
			groomer.setId(groomerData.getId());
			groomer.setName(groomerData.getName());
			groomers.add(groomer);
		}
		Groomer[] array = new Groomer[groomers.size()];
		for  (int i=0; i<groomers.size(); i++){
			array[i] = groomers.get(i);
		}
		return array;
	}
	@Override
	public Groomer create(Groomer groomer) {
		logger.info(" add:Input " + groomer.toString());
		GroomerData groomerData = new GroomerData();
		groomerData.setName(groomer.getName());
		groomerData = groomerDataRepository.save(groomerData);
		logger.info(" add:Input " + groomerData.toString());
			Groomer newGroomer = new Groomer();
			newGroomer.setId(groomerData.getId());
			newGroomer.setName(groomerData.getName());
		return newGroomer;
	}

	@Override
	public Groomer update(Groomer groomer) {
		Groomer updatedGroomer = null;
		int id = groomer.getId();
		Optional<GroomerData> optional  = groomerDataRepository.findById(groomer.getId());
		if(optional.isPresent()){
			GroomerData originalGroomerData = new GroomerData();
			originalGroomerData.setId(groomer.getId());
			originalGroomerData.setName(groomer.getName());
			originalGroomerData.setCreated(optional.get().getCreated());
			GroomerData groomerData = groomerDataRepository.save(originalGroomerData);
			updatedGroomer = new Groomer();
			updatedGroomer.setId(groomerData.getId());
			updatedGroomer.setName(groomerData.getName());
			updatedGroomer.setCreated(groomerData.getCreated());
			updatedGroomer.setLastUpdated(groomerData.getLastUpdated());
		}
		else {
			logger.error("Groomer record with id: " + Integer.toString(id) + " do not exist ");

		}
		return updatedGroomer;
	}

	@Override
	public Groomer get(Integer id) {
		logger.info(" Input id >> "+  Integer.toString(id) );
		Groomer groomer = null;
		Optional<GroomerData> optional = groomerDataRepository.findById(id);
		if(optional.isPresent()) {
			logger.info(" Is present >> ");
			groomer = new Groomer();
			groomer.setId(optional.get().getId());
			groomer.setName(optional.get().getName());
			groomer.setCreated(optional.get().getCreated());
			groomer.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.info(" Failed >> unable to locate id: " +  Integer.toString(id)  );
		}
		return groomer;
	}
	@Override
	public void delete(Integer id) {
		Groomer groomer = null;
		logger.info(" Input >> " +  Integer.toString(id));
		Optional<GroomerData> optional = groomerDataRepository.findById(id);
		if( optional.isPresent()) {
			GroomerData groomerDatum = optional.get();
			groomerDataRepository.delete(optional.get());
			logger.info(" Successfully deleted Groomer record with id: " + Integer.toString(id));
			groomer = new Groomer();
			groomer.setId(optional.get().getId());
			groomer.setName(optional.get().getName());
			groomer.setCreated(optional.get().getCreated());
			groomer.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.error(" Unable to locate groomer with id:" +  Integer.toString(id));
		}
	}
}
