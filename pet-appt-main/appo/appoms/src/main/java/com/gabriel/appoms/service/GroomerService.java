package com.gabriel.appoms.service;

import com.gabriel.appodata.entity.Groomer;
import com.gabriel.appoms.dto.request.GroomerRequest;

import java.util.List;

public interface GroomerService {

    Groomer createGroomer(GroomerRequest request);

    List<Groomer> getAllGroomers();

}