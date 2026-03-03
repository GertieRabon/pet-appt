package com.gabriel.appoms.service;
import com.gabriel.appoms.model.Appt;
public interface ApptService {
	Appt[] getAll() throws Exception;
	Appt get(Integer id) throws Exception;
	Appt create(Appt appt) throws Exception;
	Appt update(Appt appt) throws Exception;
	void delete(Integer id) throws Exception;
}
