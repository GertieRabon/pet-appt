package com.gabriel.appoms.service;
import com.gabriel.appoms.model.Appt;
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
public class ApptService {
	Logger logger = LoggerFactory.getLogger(ApptService.class);
	@Value("${service.api.endpoint}")
	private String endpointUrl = "http://localhost:8080/api/appt";

	protected static ApptService service= null;
	public static ApptService getService(){
		if(service == null){
			service = new ApptService();
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

	public Appt get(Integer id) {
		String url = endpointUrl + "/" + Integer.toString(id);
		logger.info("get: "  + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<Appt> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, Appt.class);
		return response.getBody();
	}

	public Appt[] getAll() {
		String url = endpointUrl;
		logger.info("getAppts: " + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<Appt[]> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, Appt[].class);
		Appt[] appts = response.getBody();
		return appts;
	}

	public Appt create(Appt appt) {
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Appt> request = new HttpEntity<>(appt, headers);
		final ResponseEntity<Appt> response =
		getRestTemplate().exchange(url, HttpMethod.PUT, request, Appt.class);
		return response.getBody();
	}
	public Appt update(Appt appt) {
		logger.info("update: " + appt.toString());
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Appt> request = new HttpEntity<>(appt, headers);
		final ResponseEntity<Appt> response =
		getRestTemplate().exchange(url, HttpMethod.POST, request, Appt.class);
		return response.getBody();
	}

	public void delete(Integer id){
		logger.info("delete: " + Integer.toString(id));
		String url = endpointUrl + " / " + Integer.toString(id);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Appt> request = new HttpEntity<>(null, headers);
		final ResponseEntity<Appt> response =
		getRestTemplate().exchange(url, HttpMethod.DELETE, request, Appt.class);
	}
}
