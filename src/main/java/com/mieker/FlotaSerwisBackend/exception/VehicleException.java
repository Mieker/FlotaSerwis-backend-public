package com.mieker.FlotaSerwisBackend.exception;

public class VehicleException extends RuntimeException {

    private VehicleError vehicleError;

    public VehicleException(VehicleError vehicleError) {
        this.vehicleError = vehicleError;
    }

    public VehicleError getVehicleError() {
        return vehicleError;
    }
}
