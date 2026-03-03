package com.gabriel.appoms.serviceimpl;
import com.gabriel.appoms.entity.OwnerData;
import com.gabriel.appoms.model.Owner;
import com.gabriel.appoms.repository.OwnerDataRepository;
import com.gabriel.appoms.service.OwnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class OwnerServiceImpl implements OwnerService {
	Logger logger = LoggerFactory.getLogger(OwnerServiceImpl.class);
	@Autowired
	OwnerDataRepository ownerDataRepository;
	@Autowired
	@Override
	public Owner[] getAll() {
		List<OwnerData> ownersData = new ArrayList<>();
		List<Owner> owners = new ArrayList<>();
		ownerDataRepository.findAll().forEach(ownersData::add);
		Iterator<OwnerData> it = ownersData.iterator();
		while(it.hasNext()) {
			OwnerData ownerData = it.next();
			Owner owner = new Owner();
			owner.setId(ownerData.getId());
			owner.setName(ownerData.getName());
			owners.add(owner);
		}
		Owner[] array = new Owner[owners.size()];
		for  (int i=0; i<owners.size(); i++){
			array[i] = owners.get(i);
		}
		return array;
	}
	@Override
	public Owner create(Owner owner) {
		logger.info(" add:Input " + owner.toString());
		OwnerData ownerData = new OwnerData();
		ownerData.setName(owner.getName());
		ownerData = ownerDataRepository.save(ownerData);
		logger.info(" add:Input " + ownerData.toString());
			Owner newOwner = new Owner();
			newOwner.setId(ownerData.getId());
			newOwner.setName(ownerData.getName());
		return newOwner;
	}

	@Override
	public Owner update(Owner owner) {
		Owner updatedOwner = null;
		int id = owner.getId();
		Optional<OwnerData> optional  = ownerDataRepository.findById(owner.getId());
		if(optional.isPresent()){
			OwnerData originalOwnerData = new OwnerData();
			originalOwnerData.setId(owner.getId());
			originalOwnerData.setName(owner.getName());
			originalOwnerData.setCreated(optional.get().getCreated());
			OwnerData ownerData = ownerDataRepository.save(originalOwnerData);
			updatedOwner = new Owner();
			updatedOwner.setId(ownerData.getId());
			updatedOwner.setName(ownerData.getName());
			updatedOwner.setCreated(ownerData.getCreated());
			updatedOwner.setLastUpdated(ownerData.getLastUpdated());
		}
		else {
			logger.error("Owner record with id: " + Integer.toString(id) + " do not exist ");

		}
		return updatedOwner;
	}

	@Override
	public Owner get(Integer id) {
		logger.info(" Input id >> "+  Integer.toString(id) );
		Owner owner = null;
		Optional<OwnerData> optional = ownerDataRepository.findById(id);
		if(optional.isPresent()) {
			logger.info(" Is present >> ");
			owner = new Owner();
			owner.setId(optional.get().getId());
			owner.setName(optional.get().getName());
			owner.setCreated(optional.get().getCreated());
			owner.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.info(" Failed >> unable to locate id: " +  Integer.toString(id)  );
		}
		return owner;
	}
	@Override
	public void delete(Integer id) {
		Owner owner = null;
		logger.info(" Input >> " +  Integer.toString(id));
		Optional<OwnerData> optional = ownerDataRepository.findById(id);
		if( optional.isPresent()) {
			OwnerData ownerDatum = optional.get();
			ownerDataRepository.delete(optional.get());
			logger.info(" Successfully deleted Owner record with id: " + Integer.toString(id));
			owner = new Owner();
			owner.setId(optional.get().getId());
			owner.setName(optional.get().getName());
			owner.setCreated(optional.get().getCreated());
			owner.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.error(" Unable to locate owner with id:" +  Integer.toString(id));
		}
	}
}
