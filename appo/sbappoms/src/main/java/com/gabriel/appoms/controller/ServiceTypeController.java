package com.gabriel.appoms.controller;
import com.gabriel.appoms.model.ServiceType;
import com.gabriel.appoms.service.ServiceTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class ServiceTypeController {
	Logger logger = LoggerFactory.getLogger( ServiceTypeController.class);
	@Autowired
	private ServiceTypeService serviceTypeService;
	@GetMapping("/api/serviceType")
	public ResponseEntity<?> listServiceType()
{
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			ServiceType[] serviceType = serviceTypeService.getAll();
			response =  ResponseEntity.ok().headers(headers).body(serviceType);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PutMapping("api/serviceType")
	public ResponseEntity<?> add(@RequestBody ServiceType serviceType){
		logger.info("Input >> " + serviceType.toString() );
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			ServiceType newServiceType = serviceTypeService.create(serviceType);
			logger.info("created serviceType >> " + newServiceType.toString() );
			response = ResponseEntity.ok(newServiceType);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve serviceType with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PostMapping("api/serviceType")
	public ResponseEntity<?> update(@RequestBody ServiceType serviceType){
		logger.info("Update Input >> serviceType.toString() ");
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			ServiceType newServiceType = serviceTypeService.update(serviceType);
			response = ResponseEntity.ok(serviceType);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve serviceType with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}

	@GetMapping("api/serviceType/{id}")
	public ResponseEntity<?> get(@PathVariable final Integer id){
		logger.info("Input serviceType id >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			ServiceType serviceType = serviceTypeService.get(id);
			response = ResponseEntity.ok(serviceType);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@DeleteMapping("api/serviceType/{id}")
	public ResponseEntity<?> delete(@PathVariable final Integer id){
		logger.info("Input >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			serviceTypeService.delete(id);
			response = ResponseEntity.ok(null);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
}
