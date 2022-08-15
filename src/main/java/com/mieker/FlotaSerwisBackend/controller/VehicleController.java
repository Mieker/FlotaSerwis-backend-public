package com.mieker.FlotaSerwisBackend.controller;

import com.mieker.FlotaSerwisBackend.model.Malfunction;
import com.mieker.FlotaSerwisBackend.model.Vehicle;
import com.mieker.FlotaSerwisBackend.service.MalfunctionService;
import com.mieker.FlotaSerwisBackend.service.VehicleService;
import com.mieker.FlotaSerwisBackend.validation.PatchVehicleValidation;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;
    private final MalfunctionService malfunctionService;

    public VehicleController(VehicleService vehicleService, MalfunctionService malfunctionService) {
        this.vehicleService = vehicleService;
        this.malfunctionService = malfunctionService;
    }

    @GetMapping
    public List<Vehicle> getAllVehicles(@RequestParam(required = false) Boolean isArchived) {
        return new ArrayList<>(vehicleService.getAllVehicles(isArchived));
    }

    @GetMapping("/{vehicleId}")
    public Vehicle getVehicleById(@PathVariable String vehicleId) {
        return vehicleService.getVehicleById(vehicleId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vehicle addVehicle(@RequestBody @Valid Vehicle vehicle) {
        return vehicleService.addVehicle(vehicle);
    }

    @PatchMapping("/{vehicleId}")
    public Vehicle modifyVehicle(@PathVariable String vehicleId, @RequestBody @Validated(PatchVehicleValidation.class) Vehicle vehicle) {
        return vehicleService.modifyVehicle(vehicleId, vehicle);
    }

    @PatchMapping("/{vehicleId}/switch_technical_efficiency")
    public Vehicle switchTechnicalEfficiency(@PathVariable String vehicleId) {
        return vehicleService.switchTechnicalEfficiency(vehicleId);
    }

    @PatchMapping("/{vehicleId}/archive")
    public Vehicle archiveVehicle(@PathVariable String vehicleId) {
        return vehicleService.archiveVehicle(vehicleId);
    }

    @PostMapping("/{vehicleId}/malfunctions")
    @ResponseStatus(HttpStatus.CREATED)
    public Vehicle addMalfunctionToVehicle(@PathVariable String vehicleId, @RequestBody @Valid Malfunction malfunction) {
        Malfunction addedMalfunction = malfunctionService.addMalfunctionToDataBase(malfunction);
        return vehicleService.addMalfunctionToVehicle(vehicleId, addedMalfunction);
    }


}
