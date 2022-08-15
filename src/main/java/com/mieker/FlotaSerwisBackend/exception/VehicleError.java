package com.mieker.FlotaSerwisBackend.exception;

public enum VehicleError {

    VEHICLE_MODEL_NAME_IS_NOT_VALID("Nieprawidłowa nazwa pojazdu."),
    VEHICLE_REGISTRATION_NUMBER_ALREADY_EXISTS("Pojazd z tym numerem rejestracyjnym znajduje się już w bazie danych."),
    VEHICLE_VIN_ALREADY_EXISTS("Pojazd z tym VIN znajduje się już w bazie danych."),
    VEHICLE_VIN_CONTAINS_WRONG_CHARACTERS("Vin pojazdu zawiera nieprawidłowe znaki."),
    VEHICLE_REGISTRATION_CONTAINS_WRONG_CHARACTERS("Numer rejestracyjny zawiera nieprawidłowe znaki."),
    VEHICLE_REGISTRATION_WRONG_PATTERN("Nieprawidłowy format numeru rejestracyjnego.");

    private String message;

    VehicleError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
