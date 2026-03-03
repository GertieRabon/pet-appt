package com.gabriel.appoms.repository;
import com.gabriel.appoms.entity.AppointmentData;
import org.springframework.data.repository.CrudRepository;
public interface AppointmentDataRepository extends CrudRepository<AppointmentData,Integer> {}