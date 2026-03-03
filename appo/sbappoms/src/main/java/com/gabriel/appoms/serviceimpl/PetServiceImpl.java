package com.gabriel.appoms.serviceimpl;
import com.gabriel.appoms.entity.PetData;
import com.gabriel.appoms.model.Pet;
import com.gabriel.appoms.repository.PetDataRepository;
import com.gabriel.appoms.service.PetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class PetServiceImpl implements PetService {
	Logger logger = LoggerFactory.getLogger(PetServiceImpl.class);
	@Autowired
	PetDataRepository petDataRepository;
	@Autowired
	@Override
	public Pet[] getAll() {
		List<PetData> petsData = new ArrayList<>();
		List<Pet> pets = new ArrayList<>();
		petDataRepository.findAll().forEach(petsData::add);
		Iterator<PetData> it = petsData.iterator();
		while(it.hasNext()) {
			PetData petData = it.next();
			Pet pet = new Pet();
			pet.setId(petData.getId());
			pet.setName(petData.getName());
			pets.add(pet);
		}
		Pet[] array = new Pet[pets.size()];
		for  (int i=0; i<pets.size(); i++){
			array[i] = pets.get(i);
		}
		return array;
	}
	@Override
	public Pet create(Pet pet) {
		logger.info(" add:Input " + pet.toString());
		PetData petData = new PetData();
		petData.setName(pet.getName());
		petData = petDataRepository.save(petData);
		logger.info(" add:Input " + petData.toString());
			Pet newPet = new Pet();
			newPet.setId(petData.getId());
			newPet.setName(petData.getName());
		return newPet;
	}

	@Override
	public Pet update(Pet pet) {
		Pet updatedPet = null;
		int id = pet.getId();
		Optional<PetData> optional  = petDataRepository.findById(pet.getId());
		if(optional.isPresent()){
			PetData originalPetData = new PetData();
			originalPetData.setId(pet.getId());
			originalPetData.setName(pet.getName());
			originalPetData.setCreated(optional.get().getCreated());
			PetData petData = petDataRepository.save(originalPetData);
			updatedPet = new Pet();
			updatedPet.setId(petData.getId());
			updatedPet.setName(petData.getName());
			updatedPet.setCreated(petData.getCreated());
			updatedPet.setLastUpdated(petData.getLastUpdated());
		}
		else {
			logger.error("Pet record with id: " + Integer.toString(id) + " do not exist ");

		}
		return updatedPet;
	}

	@Override
	public Pet get(Integer id) {
		logger.info(" Input id >> "+  Integer.toString(id) );
		Pet pet = null;
		Optional<PetData> optional = petDataRepository.findById(id);
		if(optional.isPresent()) {
			logger.info(" Is present >> ");
			pet = new Pet();
			pet.setId(optional.get().getId());
			pet.setName(optional.get().getName());
			pet.setCreated(optional.get().getCreated());
			pet.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.info(" Failed >> unable to locate id: " +  Integer.toString(id)  );
		}
		return pet;
	}
	@Override
	public void delete(Integer id) {
		Pet pet = null;
		logger.info(" Input >> " +  Integer.toString(id));
		Optional<PetData> optional = petDataRepository.findById(id);
		if( optional.isPresent()) {
			PetData petDatum = optional.get();
			petDataRepository.delete(optional.get());
			logger.info(" Successfully deleted Pet record with id: " + Integer.toString(id));
			pet = new Pet();
			pet.setId(optional.get().getId());
			pet.setName(optional.get().getName());
			pet.setCreated(optional.get().getCreated());
			pet.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.error(" Unable to locate pet with id:" +  Integer.toString(id));
		}
	}
}
