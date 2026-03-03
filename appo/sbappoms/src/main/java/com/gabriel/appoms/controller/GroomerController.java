package com.gabriel.appoms.controller;
import com.gabriel.appoms.model.Groomer;
import com.gabriel.appoms.service.GroomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class GroomerController {
	Logger logger = LoggerFactory.getLogger( GroomerController.class);
	@Autowired
	private GroomerService groomerService;
	@GetMapping("/api/groomer")
	public ResponseEntity<?> listGroomer()
{
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Groomer[] groomer = groomerService.getAll();
			response =  ResponseEntity.ok().headers(headers).body(groomer);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PutMapping("api/groomer")
	public ResponseEntity<?> add(@RequestBody Groomer groomer){
		logger.info("Input >> " + groomer.toString() );
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Groomer newGroomer = groomerService.create(groomer);
			logger.info("created groomer >> " + newGroomer.toString() );
			response = ResponseEntity.ok(newGroomer);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve groomer with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PostMapping("api/groomer")
	public ResponseEntity<?> update(@RequestBody Groomer groomer){
		logger.info("Update Input >> groomer.toString() ");
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Groomer newGroomer = groomerService.update(groomer);
			response = ResponseEntity.ok(groomer);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve groomer with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}

	@GetMapping("api/groomer/{id}")
	public ResponseEntity<?> get(@PathVariable final Integer id){
		logger.info("Input groomer id >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Groomer groomer = groomerService.get(id);
			response = ResponseEntity.ok(groomer);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@DeleteMapping("api/groomer/{id}")
	public ResponseEntity<?> delete(@PathVariable final Integer id){
		logger.info("Input >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			groomerService.delete(id);
			response = ResponseEntity.ok(null);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
}
