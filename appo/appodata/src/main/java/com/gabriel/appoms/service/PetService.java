package com.gabriel.appoms.service;
import com.gabriel.appoms.model.Pet;
public interface PetService {
	Pet[] getAll() throws Exception;
	Pet get(Integer id) throws Exception;
	Pet create(Pet pet) throws Exception;
	Pet update(Pet pet) throws Exception;
	void delete(Integer id) throws Exception;
}
