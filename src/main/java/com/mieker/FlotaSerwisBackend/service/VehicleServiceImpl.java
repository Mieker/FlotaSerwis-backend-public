package com.mieker.FlotaSerwisBackend.service;

import com.mieker.FlotaSerwisBackend.model.Malfunction;
import com.mieker.FlotaSerwisBackend.model.Vehicle;
import com.mieker.FlotaSerwisBackend.model.dto.EmailDto;
import com.mieker.FlotaSerwisBackend.repository.VehicleRepository;
import com.mieker.FlotaSerwisBackend.validation.VehicleValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleValidator vehicleValidator;
    private final EmailServiceClient emailServiceClient;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, VehicleValidator vehicleValidator, EmailServiceClient emailServiceClient) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleValidator = vehicleValidator;
        this.emailServiceClient = emailServiceClient;
    }

    @Override
    public List<Vehicle> getAllVehicles(Boolean isArchived) {
        if (isArchived == null) {
            return vehicleRepository.findAll();
        } else {
            return vehicleRepository.findAll().stream().filter(vehicle -> vehicle.isArchived() == isArchived).toList();
        }
    }

    @Override
    public Vehicle getVehicleById(String id) {
        return vehicleRepository.findById(id);
    }

    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        vehicleValidator.verifyVehicleInput(vehicle);
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        EmailDto addVehicleNotification = prepareAddVehicleNotification(savedVehicle);
        emailServiceClient.sendEmail(addVehicleNotification);
        return vehicle;

    }

    private EmailDto prepareAddVehicleNotification(Vehicle vehicle) {
        String title = "FlotaSerwis: dodano pojazd: " + vehicle.getModelName() + ", nr rej. " + vehicle.getRegistrationNumber();
        String body = "Witaj " + vehicle.getUser() + "!\n\nMiło nam poinformować, że pojazd " + vehicle.getModelName() + " o numerze rejestracyjnym: "
                + vehicle.getRegistrationNumber() + " właśnie został dodany do naszej bazy danych.\n\nSzczegóły możesz sprawdzić na: https://flotaserwis.herokuapp.com/vehicle-details/"
                + vehicle.getId() + "\n\nŻyczymy bezpiecznej eksploatacji.\n\nZ poważaniem,\nZespół FlotaSerwis";
        
        return EmailDto.builder().email(vehicle.getUsersEmail()).title(title).body(body).build();
    }

    @Override
    public Vehicle modifyVehicle(String vehicleId, Vehicle vehiclePatch) {
        Vehicle existingVehicle = vehicleRepository.findById(vehicleId);
        vehicleValidator.verifyVehicleInput(vehiclePatch);
        if (vehiclePatch.getCategory() != null && !vehiclePatch.getCategory().toString().isEmpty()) {
            existingVehicle.setCategory(vehiclePatch.getCategory());
        }
        if (vehiclePatch.getModelName() != null && !vehiclePatch.getModelName().isEmpty()) {
            existingVehicle.setModelName(vehiclePatch.getModelName());
        }
        if (vehiclePatch.getRegistrationNumber() != null && !vehiclePatch.getRegistrationNumber().isEmpty()) {
            existingVehicle.setRegistrationNumber(vehiclePatch.getRegistrationNumber());
        }
        if (vehiclePatch.getVin() != null && !vehiclePatch.getVin().isEmpty()) {
            existingVehicle.setVin(vehiclePatch.getVin());
        }
        if (vehiclePatch.getYearOfProduction() != null) {
            existingVehicle.setYearOfProduction(vehiclePatch.getYearOfProduction());
        }
        if (vehiclePatch.getUser() != null && !vehiclePatch.getUser().isEmpty()) {
            existingVehicle.setUser(vehiclePatch.getUser());
        }
        if (vehiclePatch.getIsTechnicallyEfficient() != null && !vehiclePatch.getIsTechnicallyEfficient().toString().isEmpty()) {
            existingVehicle.setIsTechnicallyEfficient(vehiclePatch.getIsTechnicallyEfficient());
        }
        if (vehiclePatch.getPhotoUrl() != null && !vehiclePatch.getPhotoUrl().isEmpty()) {
            existingVehicle.setPhotoUrl(vehiclePatch.getPhotoUrl());
        }
        return vehicleRepository.save(existingVehicle);

    }

    @Override
    public Vehicle switchTechnicalEfficiency(String vehicleId) {
        Vehicle vehicleToSwitchEfficiency = vehicleRepository.findById(vehicleId);
        if (vehicleToSwitchEfficiency.getIsTechnicallyEfficient()) {
            vehicleToSwitchEfficiency.setIsTechnicallyEfficient(false);
        } else {
            vehicleToSwitchEfficiency.setIsTechnicallyEfficient(true);
        }
        return vehicleRepository.save(vehicleToSwitchEfficiency);
    }

    @Override
    public Vehicle archiveVehicle(String id) {
        Vehicle vehicleToArchive = vehicleRepository.findById(id);
        vehicleToArchive.setArchived(true);
        return vehicleRepository.save(vehicleToArchive);
    }

    @Override
    public Vehicle addMalfunctionToVehicle(String vehicleId, Malfunction malfunction) {
        Vehicle chosenVehicle = vehicleRepository.findById(vehicleId);
        chosenVehicle.addMalfunction(malfunction);
        return vehicleRepository.save(chosenVehicle);
    }


}
