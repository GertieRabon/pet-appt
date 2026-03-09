package com.gabriel.appoms.service;

import com.gabriel.appodata.entity.Owner;
import com.gabriel.appoms.dto.request.OwnerRequest;

import java.util.List;

public interface OwnerService {

    Owner createOwner(OwnerRequest request);

    List<Owner> getAllOwners();

    Owner getOwnerById(Long id);

    Owner updateOwner(Long id, OwnerRequest request);

    void deleteOwner(Long id);
}