package com.gabriel.appoms.service;
import com.gabriel.appoms.model.ServiceType;
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
public class ServiceTypeService {
	Logger logger = LoggerFactory.getLogger(ServiceTypeService.class);
	@Value("${service.api.endpoint}")
	private String endpointUrl = "http://localhost:8080/api/serviceType";

	protected static ServiceTypeService service= null;
	public static ServiceTypeService getService(){
		if(service == null){
			service = new ServiceTypeService();
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

	public ServiceType get(Integer id) {
		String url = endpointUrl + "/" + Integer.toString(id);
		logger.info("get: "  + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<ServiceType> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, ServiceType.class);
		return response.getBody();
	}

	public ServiceType[] getAll() {
		String url = endpointUrl;
		logger.info("getServiceTypes: " + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<ServiceType[]> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, ServiceType[].class);
		ServiceType[] serviceTypes = response.getBody();
		return serviceTypes;
	}

	public ServiceType create(ServiceType serviceType) {
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<ServiceType> request = new HttpEntity<>(serviceType, headers);
		final ResponseEntity<ServiceType> response =
		getRestTemplate().exchange(url, HttpMethod.PUT, request, ServiceType.class);
		return response.getBody();
	}
	public ServiceType update(ServiceType serviceType) {
		logger.info("update: " + serviceType.toString());
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<ServiceType> request = new HttpEntity<>(serviceType, headers);
		final ResponseEntity<ServiceType> response =
		getRestTemplate().exchange(url, HttpMethod.POST, request, ServiceType.class);
		return response.getBody();
	}

	public void delete(Integer id){
		logger.info("delete: " + Integer.toString(id));
		String url = endpointUrl + " / " + Integer.toString(id);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<ServiceType> request = new HttpEntity<>(null, headers);
		final ResponseEntity<ServiceType> response =
		getRestTemplate().exchange(url, HttpMethod.DELETE, request, ServiceType.class);
	}
}
