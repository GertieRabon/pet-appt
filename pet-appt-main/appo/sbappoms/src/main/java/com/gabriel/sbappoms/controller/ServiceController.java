// =================== ServiceController.java ===================
package com.gabriel.sbappoms.controller;

import com.gabriel.appodata.entity.ServiceType;
import com.gabriel.appodata.repository.ServiceRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
@Tag(name = "Services", description = "Manage grooming service types")
@SecurityRequirement(name = "BearerAuth")
public class ServiceController {

    private final ServiceRepository serviceRepository;

    public ServiceController(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Add a new grooming service (Admin only)")
    public ServiceType create(@RequestBody ServiceType service) {
        return serviceRepository.save(service);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    @Operation(summary = "Get all available grooming services")
    public List<ServiceType> getAll() {
        return serviceRepository.findAll();
    }
}