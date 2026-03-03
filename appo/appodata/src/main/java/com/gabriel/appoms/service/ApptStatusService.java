package com.gabriel.appoms.service;
import com.gabriel.appoms.model.ApptStatus;
public interface ApptStatusService {
	ApptStatus[] getAll() throws Exception;
	ApptStatus get(Integer id) throws Exception;
	ApptStatus create(ApptStatus apptStatus) throws Exception;
	ApptStatus update(ApptStatus apptStatus) throws Exception;
	void delete(Integer id) throws Exception;
}
