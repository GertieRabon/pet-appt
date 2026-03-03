package com.gabriel.appoms.service;
import com.gabriel.appoms.model.ServiceType;
public interface ServiceTypeService {
	ServiceType[] getAll() throws Exception;
	ServiceType get(Integer id) throws Exception;
	ServiceType create(ServiceType serviceType) throws Exception;
	ServiceType update(ServiceType serviceType) throws Exception;
	void delete(Integer id) throws Exception;
}
