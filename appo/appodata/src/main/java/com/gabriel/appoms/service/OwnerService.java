package com.gabriel.appoms.service;
import com.gabriel.appoms.model.Owner;
public interface OwnerService {
	Owner[] getAll() throws Exception;
	Owner get(Integer id) throws Exception;
	Owner create(Owner owner) throws Exception;
	Owner update(Owner owner) throws Exception;
	void delete(Integer id) throws Exception;
}
