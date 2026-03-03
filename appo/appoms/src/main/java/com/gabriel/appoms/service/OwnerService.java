package com.gabriel.appoms.service;
import com.gabriel.appoms.model.Owner;
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
public class OwnerService {
	Logger logger = LoggerFactory.getLogger(OwnerService.class);
	@Value("${service.api.endpoint}")
	private String endpointUrl = "http://localhost:8080/api/owner";

	protected static OwnerService service= null;
	public static OwnerService getService(){
		if(service == null){
			service = new OwnerService();
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

	public Owner get(Integer id) {
		String url = endpointUrl + "/" + Integer.toString(id);
		logger.info("get: "  + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<Owner> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, Owner.class);
		return response.getBody();
	}

	public Owner[] getAll() {
		String url = endpointUrl;
		logger.info("getOwners: " + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<Owner[]> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, Owner[].class);
		Owner[] owners = response.getBody();
		return owners;
	}

	public Owner create(Owner owner) {
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Owner> request = new HttpEntity<>(owner, headers);
		final ResponseEntity<Owner> response =
		getRestTemplate().exchange(url, HttpMethod.PUT, request, Owner.class);
		return response.getBody();
	}
	public Owner update(Owner owner) {
		logger.info("update: " + owner.toString());
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Owner> request = new HttpEntity<>(owner, headers);
		final ResponseEntity<Owner> response =
		getRestTemplate().exchange(url, HttpMethod.POST, request, Owner.class);
		return response.getBody();
	}

	public void delete(Integer id){
		logger.info("delete: " + Integer.toString(id));
		String url = endpointUrl + " / " + Integer.toString(id);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Owner> request = new HttpEntity<>(null, headers);
		final ResponseEntity<Owner> response =
		getRestTemplate().exchange(url, HttpMethod.DELETE, request, Owner.class);
	}
}
