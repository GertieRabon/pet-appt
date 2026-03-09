package com.gabriel.sbappoms.controller;

import com.gabriel.appodata.entity.Owner;
import com.gabriel.appoms.dto.request.OwnerRequest;
import com.gabriel.appoms.service.OwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners")
@Tag(name = "Owners", description = "Manage pet owners")
@SecurityRequirement(name = "BearerAuth")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    @Operation(summary = "Register a new owner profile")
    public Owner createOwner(@RequestBody OwnerRequest request) {
        return ownerService.createOwner(request);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all owners (Admin only)")
    public List<Owner> getAllOwners() {
        return ownerService.getAllOwners();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    @Operation(summary = "Get an owner by ID")
    public Owner getOwnerById(@PathVariable Long id) {
        return ownerService.getOwnerById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    @Operation(summary = "Update an owner profile")
    public Owner updateOwner(@PathVariable Long id, @RequestBody OwnerRequest request) {
        return ownerService.updateOwner(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete an owner (Admin only)")
    public void deleteOwner(@PathVariable Long id) {
        ownerService.deleteOwner(id);
    }
}