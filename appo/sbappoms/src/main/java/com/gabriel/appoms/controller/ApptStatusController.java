package com.gabriel.appoms.controller;
import com.gabriel.appoms.model.ApptStatus;
import com.gabriel.appoms.service.ApptStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class ApptStatusController {
	Logger logger = LoggerFactory.getLogger( ApptStatusController.class);
	@Autowired
	private ApptStatusService apptStatusService;
	@GetMapping("/api/apptStatus")
	public ResponseEntity<?> listApptStatus()
{
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			ApptStatus[] apptStatus = apptStatusService.getAll();
			response =  ResponseEntity.ok().headers(headers).body(apptStatus);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PutMapping("api/apptStatus")
	public ResponseEntity<?> add(@RequestBody ApptStatus apptStatus){
		logger.info("Input >> " + apptStatus.toString() );
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			ApptStatus newApptStatus = apptStatusService.create(apptStatus);
			logger.info("created apptStatus >> " + newApptStatus.toString() );
			response = ResponseEntity.ok(newApptStatus);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve apptStatus with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PostMapping("api/apptStatus")
	public ResponseEntity<?> update(@RequestBody ApptStatus apptStatus){
		logger.info("Update Input >> apptStatus.toString() ");
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			ApptStatus newApptStatus = apptStatusService.update(apptStatus);
			response = ResponseEntity.ok(apptStatus);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve apptStatus with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}

	@GetMapping("api/apptStatus/{id}")
	public ResponseEntity<?> get(@PathVariable final Integer id){
		logger.info("Input apptStatus id >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			ApptStatus apptStatus = apptStatusService.get(id);
			response = ResponseEntity.ok(apptStatus);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@DeleteMapping("api/apptStatus/{id}")
	public ResponseEntity<?> delete(@PathVariable final Integer id){
		logger.info("Input >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			apptStatusService.delete(id);
			response = ResponseEntity.ok(null);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
}
