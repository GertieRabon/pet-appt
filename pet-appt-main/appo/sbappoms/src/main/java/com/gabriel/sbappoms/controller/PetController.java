// =================== PetController.java ===================
package com.gabriel.sbappoms.controller;

import com.gabriel.appodata.entity.Pet;
import com.gabriel.appoms.dto.request.PetRequest;
import com.gabriel.appoms.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
@Tag(name = "Pets", description = "Manage pets for grooming appointments")
@SecurityRequirement(name = "BearerAuth")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    @Operation(summary = "Register a new pet")
    public Pet createPet(@RequestBody PetRequest request) {
        return petService.createPet(request);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all pets (Admin only)")
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    @Operation(summary = "Get a pet by ID")
    public Pet getPet(@PathVariable Long id) {
        return petService.getPetById(id);
    }

    @GetMapping("/owner/{ownerId}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    @Operation(summary = "Get all pets belonging to an owner")
    public List<Pet> getPetsByOwner(@PathVariable Long ownerId) {
        return petService.getPetsByOwner(ownerId);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    @Operation(summary = "Delete a pet")
    public String deletePet(@PathVariable Long id) {
        petService.deletePet(id);
        return "Pet deleted";
    }
}