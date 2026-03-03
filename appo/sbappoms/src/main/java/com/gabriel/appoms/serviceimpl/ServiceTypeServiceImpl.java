package com.gabriel.appoms.serviceimpl;
import com.gabriel.appoms.entity.ServiceTypeData;
import com.gabriel.appoms.model.ServiceType;
import com.gabriel.appoms.repository.ServiceTypeDataRepository;
import com.gabriel.appoms.service.ServiceTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class ServiceTypeServiceImpl implements ServiceTypeService {
	Logger logger = LoggerFactory.getLogger(ServiceTypeServiceImpl.class);
	@Autowired
	ServiceTypeDataRepository serviceTypeDataRepository;
	@Autowired
	@Override
	public ServiceType[] getAll() {
		List<ServiceTypeData> serviceTypesData = new ArrayList<>();
		List<ServiceType> serviceTypes = new ArrayList<>();
		serviceTypeDataRepository.findAll().forEach(serviceTypesData::add);
		Iterator<ServiceTypeData> it = serviceTypesData.iterator();
		while(it.hasNext()) {
			ServiceTypeData serviceTypeData = it.next();
			ServiceType serviceType = new ServiceType();
			serviceType.setId(serviceTypeData.getId());
			serviceType.setName(serviceTypeData.getName());
			serviceTypes.add(serviceType);
		}
		ServiceType[] array = new ServiceType[serviceTypes.size()];
		for  (int i=0; i<serviceTypes.size(); i++){
			array[i] = serviceTypes.get(i);
		}
		return array;
	}
	@Override
	public ServiceType create(ServiceType serviceType) {
		logger.info(" add:Input " + serviceType.toString());
		ServiceTypeData serviceTypeData = new ServiceTypeData();
		serviceTypeData.setName(serviceType.getName());
		serviceTypeData = serviceTypeDataRepository.save(serviceTypeData);
		logger.info(" add:Input " + serviceTypeData.toString());
			ServiceType newServiceType = new ServiceType();
			newServiceType.setId(serviceTypeData.getId());
			newServiceType.setName(serviceTypeData.getName());
		return newServiceType;
	}

	@Override
	public ServiceType update(ServiceType serviceType) {
		ServiceType updatedServiceType = null;
		int id = serviceType.getId();
		Optional<ServiceTypeData> optional  = serviceTypeDataRepository.findById(serviceType.getId());
		if(optional.isPresent()){
			ServiceTypeData originalServiceTypeData = new ServiceTypeData();
			originalServiceTypeData.setId(serviceType.getId());
			originalServiceTypeData.setName(serviceType.getName());
			originalServiceTypeData.setCreated(optional.get().getCreated());
			ServiceTypeData serviceTypeData = serviceTypeDataRepository.save(originalServiceTypeData);
			updatedServiceType = new ServiceType();
			updatedServiceType.setId(serviceTypeData.getId());
			updatedServiceType.setName(serviceTypeData.getName());
			updatedServiceType.setCreated(serviceTypeData.getCreated());
			updatedServiceType.setLastUpdated(serviceTypeData.getLastUpdated());
		}
		else {
			logger.error("ServiceType record with id: " + Integer.toString(id) + " do not exist ");

		}
		return updatedServiceType;
	}

	@Override
	public ServiceType get(Integer id) {
		logger.info(" Input id >> "+  Integer.toString(id) );
		ServiceType serviceType = null;
		Optional<ServiceTypeData> optional = serviceTypeDataRepository.findById(id);
		if(optional.isPresent()) {
			logger.info(" Is present >> ");
			serviceType = new ServiceType();
			serviceType.setId(optional.get().getId());
			serviceType.setName(optional.get().getName());
			serviceType.setCreated(optional.get().getCreated());
			serviceType.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.info(" Failed >> unable to locate id: " +  Integer.toString(id)  );
		}
		return serviceType;
	}
	@Override
	public void delete(Integer id) {
		ServiceType serviceType = null;
		logger.info(" Input >> " +  Integer.toString(id));
		Optional<ServiceTypeData> optional = serviceTypeDataRepository.findById(id);
		if( optional.isPresent()) {
			ServiceTypeData serviceTypeDatum = optional.get();
			serviceTypeDataRepository.delete(optional.get());
			logger.info(" Successfully deleted ServiceType record with id: " + Integer.toString(id));
			serviceType = new ServiceType();
			serviceType.setId(optional.get().getId());
			serviceType.setName(optional.get().getName());
			serviceType.setCreated(optional.get().getCreated());
			serviceType.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.error(" Unable to locate serviceType with id:" +  Integer.toString(id));
		}
	}
}
