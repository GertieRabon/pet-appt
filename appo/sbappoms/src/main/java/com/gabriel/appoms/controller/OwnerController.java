package com.gabriel.appoms.controller;
import com.gabriel.appoms.model.Owner;
import com.gabriel.appoms.service.OwnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class OwnerController {
	Logger logger = LoggerFactory.getLogger( OwnerController.class);
	@Autowired
	private OwnerService ownerService;
	@GetMapping("/api/owner")
	public ResponseEntity<?> listOwner()
{
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Owner[] owner = ownerService.getAll();
			response =  ResponseEntity.ok().headers(headers).body(owner);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PutMapping("api/owner")
	public ResponseEntity<?> add(@RequestBody Owner owner){
		logger.info("Input >> " + owner.toString() );
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Owner newOwner = ownerService.create(owner);
			logger.info("created owner >> " + newOwner.toString() );
			response = ResponseEntity.ok(newOwner);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve owner with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PostMapping("api/owner")
	public ResponseEntity<?> update(@RequestBody Owner owner){
		logger.info("Update Input >> owner.toString() ");
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Owner newOwner = ownerService.update(owner);
			response = ResponseEntity.ok(owner);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve owner with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}

	@GetMapping("api/owner/{id}")
	public ResponseEntity<?> get(@PathVariable final Integer id){
		logger.info("Input owner id >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Owner owner = ownerService.get(id);
			response = ResponseEntity.ok(owner);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@DeleteMapping("api/owner/{id}")
	public ResponseEntity<?> delete(@PathVariable final Integer id){
		logger.info("Input >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			ownerService.delete(id);
			response = ResponseEntity.ok(null);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
}
