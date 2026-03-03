package com.gabriel.appoms.service;
import com.gabriel.appoms.model.ApptStatus;
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
public class ApptStatusService {
	Logger logger = LoggerFactory.getLogger(ApptStatusService.class);
	@Value("${service.api.endpoint}")
	private String endpointUrl = "http://localhost:8080/api/apptStatus";

	protected static ApptStatusService service= null;
	public static ApptStatusService getService(){
		if(service == null){
			service = new ApptStatusService();
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

	public ApptStatus get(Integer id) {
		String url = endpointUrl + "/" + Integer.toString(id);
		logger.info("get: "  + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<ApptStatus> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, ApptStatus.class);
		return response.getBody();
	}

	public ApptStatus[] getAll() {
		String url = endpointUrl;
		logger.info("getApptStatuss: " + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<ApptStatus[]> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, ApptStatus[].class);
		ApptStatus[] apptStatuss = response.getBody();
		return apptStatuss;
	}

	public ApptStatus create(ApptStatus apptStatus) {
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<ApptStatus> request = new HttpEntity<>(apptStatus, headers);
		final ResponseEntity<ApptStatus> response =
		getRestTemplate().exchange(url, HttpMethod.PUT, request, ApptStatus.class);
		return response.getBody();
	}
	public ApptStatus update(ApptStatus apptStatus) {
		logger.info("update: " + apptStatus.toString());
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<ApptStatus> request = new HttpEntity<>(apptStatus, headers);
		final ResponseEntity<ApptStatus> response =
		getRestTemplate().exchange(url, HttpMethod.POST, request, ApptStatus.class);
		return response.getBody();
	}

	public void delete(Integer id){
		logger.info("delete: " + Integer.toString(id));
		String url = endpointUrl + " / " + Integer.toString(id);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<ApptStatus> request = new HttpEntity<>(null, headers);
		final ResponseEntity<ApptStatus> response =
		getRestTemplate().exchange(url, HttpMethod.DELETE, request, ApptStatus.class);
	}
}
