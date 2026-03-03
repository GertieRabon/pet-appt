package com.gabriel.appoms.controller;
import com.gabriel.appoms.model.Appt;
import com.gabriel.appoms.service.ApptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class ApptController {
	Logger logger = LoggerFactory.getLogger( ApptController.class);
	@Autowired
	private ApptService apptService;
	@GetMapping("/api/appt")
	public ResponseEntity<?> listAppt()
{
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Appt[] appt = apptService.getAll();
			response =  ResponseEntity.ok().headers(headers).body(appt);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PutMapping("api/appt")
	public ResponseEntity<?> add(@RequestBody Appt appt){
		logger.info("Input >> " + appt.toString() );
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Appt newAppt = apptService.create(appt);
			logger.info("created appt >> " + newAppt.toString() );
			response = ResponseEntity.ok(newAppt);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve appt with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PostMapping("api/appt")
	public ResponseEntity<?> update(@RequestBody Appt appt){
		logger.info("Update Input >> appt.toString() ");
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Appt newAppt = apptService.update(appt);
			response = ResponseEntity.ok(appt);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve appt with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}

	@GetMapping("api/appt/{id}")
	public ResponseEntity<?> get(@PathVariable final Integer id){
		logger.info("Input appt id >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Appt appt = apptService.get(id);
			response = ResponseEntity.ok(appt);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@DeleteMapping("api/appt/{id}")
	public ResponseEntity<?> delete(@PathVariable final Integer id){
		logger.info("Input >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			apptService.delete(id);
			response = ResponseEntity.ok(null);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
}
