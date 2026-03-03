package com.gabriel.appoms.service;
import com.gabriel.appoms.model.Groomer;
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
public class GroomerService {
	Logger logger = LoggerFactory.getLogger(GroomerService.class);
	@Value("${service.api.endpoint}")
	private String endpointUrl = "http://localhost:8080/api/groomer";

	protected static GroomerService service= null;
	public static GroomerService getService(){
		if(service == null){
			service = new GroomerService();
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

	public Groomer get(Integer id) {
		String url = endpointUrl + "/" + Integer.toString(id);
		logger.info("get: "  + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<Groomer> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, Groomer.class);
		return response.getBody();
	}

	public Groomer[] getAll() {
		String url = endpointUrl;
		logger.info("getGroomers: " + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<Groomer[]> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, Groomer[].class);
		Groomer[] groomers = response.getBody();
		return groomers;
	}

	public Groomer create(Groomer groomer) {
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Groomer> request = new HttpEntity<>(groomer, headers);
		final ResponseEntity<Groomer> response =
		getRestTemplate().exchange(url, HttpMethod.PUT, request, Groomer.class);
		return response.getBody();
	}
	public Groomer update(Groomer groomer) {
		logger.info("update: " + groomer.toString());
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Groomer> request = new HttpEntity<>(groomer, headers);
		final ResponseEntity<Groomer> response =
		getRestTemplate().exchange(url, HttpMethod.POST, request, Groomer.class);
		return response.getBody();
	}

	public void delete(Integer id){
		logger.info("delete: " + Integer.toString(id));
		String url = endpointUrl + " / " + Integer.toString(id);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Groomer> request = new HttpEntity<>(null, headers);
		final ResponseEntity<Groomer> response =
		getRestTemplate().exchange(url, HttpMethod.DELETE, request, Groomer.class);
	}
}
