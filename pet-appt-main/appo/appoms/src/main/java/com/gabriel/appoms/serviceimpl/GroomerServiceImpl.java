package com.gabriel.appoms.serviceimpl;

import com.gabriel.appodata.entity.Groomer;
import com.gabriel.appodata.repository.GroomerRepository;
import com.gabriel.appoms.dto.request.GroomerRequest;
import com.gabriel.appoms.service.GroomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroomerServiceImpl implements GroomerService {

    private final GroomerRepository groomerRepository;

    public GroomerServiceImpl(GroomerRepository groomerRepository) {
        this.groomerRepository = groomerRepository;
    }

    @Override
    public Groomer createGroomer(GroomerRequest request) {

        Groomer groomer = new Groomer();

        groomer.setGroomerName(request.groomerName);
        groomer.setPhoneNumber(request.phoneNumber);
        groomer.setEmail(request.email);
        groomer.setExperienceYears(request.experienceYears);

        return groomerRepository.save(groomer);
    }

    @Override
    public List<Groomer> getAllGroomers() {
        return groomerRepository.findAll();
    }
}