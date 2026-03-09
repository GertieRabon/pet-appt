package com.gabriel.appoms.serviceimpl;

import com.gabriel.appodata.entity.Pet;
import com.gabriel.appodata.repository.PetRepository;
import com.gabriel.appoms.dto.request.PetRequest;
import com.gabriel.appoms.service.PetService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Pet createPet(PetRequest request) {

        Pet pet = new Pet();

        pet.setPetName(request.petName);
        pet.setPetBreed(request.petBreed);
        pet.setPetAge(request.petAge);
        pet.setMedicalNotes(request.medicalNotes);
        pet.setOwnerId(request.ownerId);
        pet.setPetType(request.petType);
        pet.setPetSize(request.petSize);

        return petRepository.save(pet);
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    public Pet getPetById(Long id) {
        return petRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Pet> getPetsByOwner(Long ownerId) {
        return petRepository.findByOwnerId(ownerId);
    }

    @Override
    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }
}