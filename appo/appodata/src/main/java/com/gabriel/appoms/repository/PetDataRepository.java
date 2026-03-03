package com.gabriel.appoms.repository;
import com.gabriel.appoms.entity.PetData;
import org.springframework.data.repository.CrudRepository;
public interface PetDataRepository extends CrudRepository<PetData,Integer> {}