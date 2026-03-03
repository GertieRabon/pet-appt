package com.gabriel.appoms.service;
import com.gabriel.appoms.model.Pet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PetService {
	Logger logger = LoggerFactory.getLogger(PetService.class);
	@Value("${service.api.endpoint}")
	private String endpointUrl = "http://localhost:8080/api/pet";

	protected static PetService service= null;
	public static PetService getService(){
		if(service == null){
			service = new PetService();
		}
		return service;
	}

	RestTemplate restTemplate = null;
	public RestTemplate getRestTemplate() {
		if(restTemplate == null) {
		restTemplate = new RestTemplate();
			List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
			MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
			converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
			messageConverters.add(converter);
			restTemplate.setMessageConverters(messageConverters);
		}
		return restTemplate;
	}

	public Pet get(Integer id) {
		String url = endpointUrl + "/" + Integer.toString(id);
		logger.info("get: "  + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<Pet> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, Pet.class);
		return response.getBody();
	}

	public Pet[] getAll() {
		String url = endpointUrl;
		logger.info("getPets: " + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<Pet[]> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, Pet[].class);
		Pet[] pets = response.getBody();
		return pets;
	}

	public Pet create(Pet pet) {
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Pet> request = new HttpEntity<>(pet, headers);
		final ResponseEntity<Pet> response =
		getRestTemplate().exchange(url, HttpMethod.PUT, request, Pet.class);
		return response.getBody();
	}
	public Pet update(Pet pet) {
		logger.info("update: " + pet.toString());
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Pet> request = new HttpEntity<>(pet, headers);
		final ResponseEntity<Pet> response =
		getRestTemplate().exchange(url, HttpMethod.POST, request, Pet.class);
		return response.getBody();
	}

	public void delete(Integer id){
		logger.info("delete: " + Integer.toString(id));
		String url = endpointUrl + " / " + Integer.toString(id);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Pet> request = new HttpEntity<>(null, headers);
		final ResponseEntity<Pet> response =
		getRestTemplate().exchange(url, HttpMethod.DELETE, request, Pet.class);
	}
}
