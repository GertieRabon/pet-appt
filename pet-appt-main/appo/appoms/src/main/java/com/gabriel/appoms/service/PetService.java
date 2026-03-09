package com.gabriel.appoms.service;

import com.gabriel.appodata.entity.Pet;
import com.gabriel.appoms.dto.request.PetRequest;

import java.util.List;

public interface PetService {

    Pet createPet(PetRequest request);

    List<Pet> getAllPets();

    Pet getPetById(Long id);

    List<Pet> getPetsByOwner(Long ownerId);

    void deletePet(Long id);
}