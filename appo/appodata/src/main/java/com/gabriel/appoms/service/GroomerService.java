package com.gabriel.appoms.service;
import com.gabriel.appoms.model.Groomer;
public interface GroomerService {
	Groomer[] getAll() throws Exception;
	Groomer get(Integer id) throws Exception;
	Groomer create(Groomer groomer) throws Exception;
	Groomer update(Groomer groomer) throws Exception;
	void delete(Integer id) throws Exception;
}
