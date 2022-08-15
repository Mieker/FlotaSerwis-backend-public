package com.mieker.FlotaSerwisBackend.validation;

import com.mieker.FlotaSerwisBackend.exception.VehicleError;
import com.mieker.FlotaSerwisBackend.exception.VehicleException;
import com.mieker.FlotaSerwisBackend.model.Vehicle;
import com.mieker.FlotaSerwisBackend.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class VehicleValidator {

    private final VehicleRepository vehicleRepository;

    public void verifyVehicleInput(Vehicle vehicle) {
        if (vehicle.getVin() != null) {
            checkIfVehicleVinExists(vehicle);
            checkIfVinContainsAllowedMarsk(vehicle);
        }
        if (vehicle.getRegistrationNumber() != null) {
            checkIfVehicleRegistrationNumberExists(vehicle);
            checkIfVehicleRegistrationNumberMatchesPatern(vehicle);
        }
        if (vehicle.getModelName() != null) {
            checkIfVehicleModelNameIsValid(vehicle);
        }
    }

    private void checkIfVehicleModelNameIsValid(Vehicle vehicle) {
        if (vehicle.getModelName().isBlank()) throw new VehicleException(VehicleError.VEHICLE_MODEL_NAME_IS_NOT_VALID);
    }

    private void checkIfVehicleVinExists(Vehicle vehicle) {
        if (vehicleRepository.existsByVin(vehicle.getVin())) {
            throw new VehicleException(VehicleError.VEHICLE_VIN_ALREADY_EXISTS);
        }
    }

    private void checkIfVehicleRegistrationNumberExists(Vehicle vehicle) {
        if (vehicleRepository.existsByRegistrationNumber(vehicle.getRegistrationNumber())) {
            throw new VehicleException(VehicleError.VEHICLE_REGISTRATION_NUMBER_ALREADY_EXISTS);
        }
    }

    private void checkIfVinContainsAllowedMarsk(Vehicle vehicle) {
        if (!vehicle.getVin().matches("[A-Z0-9]+")) {
            throw new VehicleException(VehicleError.VEHICLE_VIN_CONTAINS_WRONG_CHARACTERS);

        }
    }

    private void checkIfVehicleRegistrationNumberMatchesPatern(Vehicle vehicle) {
        String registrationNumber = vehicle.getRegistrationNumber();
        // check if contains special marks or lowercase letters
        if (!registrationNumber.matches("[A-Z0-9\\u0020]+")) {
            throw new VehicleException(VehicleError.VEHICLE_REGISTRATION_CONTAINS_WRONG_CHARACTERS);
        }
        if (Character.isDigit(registrationNumber.charAt(0))) {
            // check if first mark is digit
            throw new VehicleException(VehicleError.VEHICLE_REGISTRATION_WRONG_PATTERN);
        }
        // check if contains more than one whitespace
        int whitespaceCount = 0;
        for (char c : registrationNumber.toCharArray()) {
            if (c == ' ') {
                whitespaceCount++;
                if (whitespaceCount > 1) {
                    throw new VehicleException(VehicleError.VEHICLE_REGISTRATION_WRONG_PATTERN);
                }
                // check if whitespace is placed other than from 1 to 3 string index
                if (registrationNumber.indexOf(c) != 1 && registrationNumber.indexOf(c) != 2 &&
                        registrationNumber.indexOf(c) != 3) {
                    throw new VehicleException(VehicleError.VEHICLE_REGISTRATION_WRONG_PATTERN);
                }
            }
        }
        // check if contains less than one whitespace
        if (whitespaceCount < 1) throw new VehicleException(VehicleError.VEHICLE_REGISTRATION_WRONG_PATTERN);
    }

}
