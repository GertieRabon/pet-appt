package com.gabriel.appoms.controller;
import com.gabriel.appoms.model.Pet;
import com.gabriel.appoms.service.PetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class PetController {
	Logger logger = LoggerFactory.getLogger( PetController.class);
	@Autowired
	private PetService petService;
	@GetMapping("/api/pet")
	public ResponseEntity<?> listPet()
{
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Pet[] pet = petService.getAll();
			response =  ResponseEntity.ok().headers(headers).body(pet);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PutMapping("api/pet")
	public ResponseEntity<?> add(@RequestBody Pet pet){
		logger.info("Input >> " + pet.toString() );
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Pet newPet = petService.create(pet);
			logger.info("created pet >> " + newPet.toString() );
			response = ResponseEntity.ok(newPet);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve pet with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PostMapping("api/pet")
	public ResponseEntity<?> update(@RequestBody Pet pet){
		logger.info("Update Input >> pet.toString() ");
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Pet newPet = petService.update(pet);
			response = ResponseEntity.ok(pet);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve pet with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}

	@GetMapping("api/pet/{id}")
	public ResponseEntity<?> get(@PathVariable final Integer id){
		logger.info("Input pet id >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Pet pet = petService.get(id);
			response = ResponseEntity.ok(pet);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@DeleteMapping("api/pet/{id}")
	public ResponseEntity<?> delete(@PathVariable final Integer id){
		logger.info("Input >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			petService.delete(id);
			response = ResponseEntity.ok(null);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
}
