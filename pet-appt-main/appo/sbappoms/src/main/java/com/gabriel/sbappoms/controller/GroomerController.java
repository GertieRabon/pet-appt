// =================== GroomerController.java ===================
package com.gabriel.sbappoms.controller;

import com.gabriel.appodata.entity.Groomer;
import com.gabriel.appoms.dto.request.GroomerRequest;
import com.gabriel.appoms.service.GroomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groomers")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Groomers", description = "Manage groomers (Admin only)")
@SecurityRequirement(name = "BearerAuth")
public class GroomerController {

    private final GroomerService groomerService;

    public GroomerController(GroomerService groomerService) {
        this.groomerService = groomerService;
    }

    @PostMapping
    @Operation(summary = "Add a new groomer")
    public Groomer createGroomer(@RequestBody GroomerRequest request) {
        return groomerService.createGroomer(request);
    }

    @GetMapping
    @Operation(summary = "Get all groomers")
    public List<Groomer> getAllGroomers() {
        return groomerService.getAllGroomers();
    }
}