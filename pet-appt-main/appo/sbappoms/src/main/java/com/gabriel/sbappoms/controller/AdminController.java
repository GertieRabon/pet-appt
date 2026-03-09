package com.gabriel.sbappoms.controller;

import com.gabriel.appodata.entity.Appointment;
import com.gabriel.appodata.entity.Groomer;
import com.gabriel.appodata.entity.ServiceType;
import com.gabriel.appodata.enums.AppointmentStatus;
import com.gabriel.appodata.repository.ServiceRepository;
import com.gabriel.appoms.dto.request.GroomerRequest;
import com.gabriel.appoms.service.AdminService;
import com.gabriel.appoms.service.GroomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Admin", description = "Admin-only endpoints for managing bookings, groomers, services and revenue")
@SecurityRequirement(name = "BearerAuth")
public class AdminController {

    private final AdminService adminService;
    private final GroomerService groomerService;
    private final ServiceRepository serviceRepository;

    public AdminController(AdminService adminService,
                           GroomerService groomerService,
                           ServiceRepository serviceRepository) {
        this.adminService = adminService;
        this.groomerService = groomerService;
        this.serviceRepository = serviceRepository;
    }

    @GetMapping("/dashboard")
    @Operation(summary = "Get dashboard stats (pending, approved, completed, cancelled, rejected counts)")
    public Map<String, Long> dashboard() {
        return adminService.getDashboardStats();
    }

    @GetMapping("/bookings")
    @Operation(summary = "Get all bookings")
    public List<Appointment> getAllBookings() {
        return adminService.getAllBookings();
    }

    @GetMapping("/bookings/status/{status}")
    @Operation(summary = "Filter bookings by status (PENDING, APPROVED, REJECTED, COMPLETED, CANCELLED)")
    public List<Appointment> filterByStatus(@PathVariable AppointmentStatus status) {
        return adminService.filterByStatus(status);
    }

    @GetMapping("/bookings/date")
    @Operation(summary = "Filter bookings by date range (e.g. ?start=2026-01-01T00:00:00&end=2026-12-31T23:59:59)")
    public List<Appointment> filterByDate(@RequestParam LocalDateTime start,
                                          @RequestParam LocalDateTime end) {
        return adminService.filterByDate(start, end);
    }

    @PutMapping("/bookings/{id}/approve")
    @Operation(summary = "Approve a booking")
    public Appointment approveBooking(@PathVariable Long id) {
        return adminService.approveBooking(id);
    }

    @PutMapping("/bookings/{id}/reject")
    @Operation(summary = "Reject a booking")
    public Appointment rejectBooking(@PathVariable Long id) {
        return adminService.rejectBooking(id);
    }

    @PutMapping("/bookings/{id}/assign-groomer/{groomerId}")
    @Operation(summary = "Assign a groomer to a booking")
    public Appointment assignGroomer(@PathVariable Long id,
                                     @PathVariable Integer groomerId) {
        return adminService.assignGroomer(id, groomerId);
    }

    @PostMapping("/groomers")
    @Operation(summary = "Add a new groomer")
    public Groomer createGroomer(@RequestBody GroomerRequest request) {
        return groomerService.createGroomer(request);
    }

    @GetMapping("/groomers")
    @Operation(summary = "Get all groomers")
    public List<Groomer> getGroomers() {
        return groomerService.getAllGroomers();
    }

    @PostMapping("/services")
    @Operation(summary = "Add a new grooming service")
    public ServiceType createService(@RequestBody ServiceType service) {
        return serviceRepository.save(service);
    }

    @GetMapping("/services")
    @Operation(summary = "Get all grooming services")
    public List<ServiceType> getServices() {
        return serviceRepository.findAll();
    }

    @GetMapping("/revenue")
    @Operation(summary = "Get total revenue from completed appointments")
    public Double getRevenue() {
        return adminService.getRevenue();
    }
}