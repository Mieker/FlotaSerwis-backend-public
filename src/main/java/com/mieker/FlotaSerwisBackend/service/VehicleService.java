package com.mieker.FlotaSerwisBackend.service;

import com.mieker.FlotaSerwisBackend.model.Malfunction;
import com.mieker.FlotaSerwisBackend.model.Vehicle;

import java.util.List;

public interface VehicleService {

    List<Vehicle> getAllVehicles(Boolean isArchived);

    Vehicle getVehicleById(String id);

    Vehicle addVehicle(Vehicle vehicle);

    Vehicle modifyVehicle(String vehicleId, Vehicle vehiclePatch);

    Vehicle switchTechnicalEfficiency(String vehicleId);

    Vehicle archiveVehicle(String id);

    Vehicle addMalfunctionToVehicle(String vehicleId, Malfunction malfunction);

}
