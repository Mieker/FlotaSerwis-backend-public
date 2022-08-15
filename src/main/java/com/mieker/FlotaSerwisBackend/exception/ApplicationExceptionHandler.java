package com.mieker.FlotaSerwisBackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handleException(Exception exception) {
        if (exception.getClass().equals(VehicleException.class)) {

            VehicleException vehicleException = (VehicleException) exception;
            VehicleError vehicleError = vehicleException.getVehicleError();

            if (vehicleError.equals(VehicleError.VEHICLE_REGISTRATION_NUMBER_ALREADY_EXISTS) ||
                    vehicleError.equals(VehicleError.VEHICLE_VIN_ALREADY_EXISTS) ||
                    vehicleError.equals(VehicleError.VEHICLE_VIN_CONTAINS_WRONG_CHARACTERS) ||
                    vehicleError.equals(VehicleError.VEHICLE_REGISTRATION_CONTAINS_WRONG_CHARACTERS) ||
                    vehicleError.equals(VehicleError.VEHICLE_REGISTRATION_WRONG_PATTERN) ||
                    vehicleError.equals(VehicleError.VEHICLE_MODEL_NAME_IS_NOT_VALID)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorInfo(vehicleError.getMessage()));

            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorInfo("Some other error"));
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorInfo(pullOutExceptionMessage(exception)));

    }

    private String pullOutExceptionMessage(Exception exception) {
        try {
            String[] exceptionMessageArray = exception.getMessage().split(";");
            int exceptionDataLength = exceptionMessageArray.length;

            int exceptionArgumentIndex = exceptionDataLength - 2;
            String exceptionArgument = exceptionMessageArray[exceptionArgumentIndex];
            int argumentIndexOfOpeningSquareBracket = exceptionArgument.indexOf('[');
            int argumentIndexOfClosingSquareBracket = exceptionArgument.indexOf(']');
            exceptionArgument = exceptionArgument.substring(argumentIndexOfOpeningSquareBracket, argumentIndexOfClosingSquareBracket + 1);

            int exceptionCauseIndex = exceptionDataLength - 1;
            String exceptionCause = exceptionMessageArray[exceptionCauseIndex];
            int causeIndexOfOpeningSquareBracket = exceptionCause.indexOf('[');
            int causetIndexOfClosingSquareBracket = exceptionCause.indexOf(']');
            exceptionCause = exceptionCause.substring(causeIndexOfOpeningSquareBracket + 1, causetIndexOfClosingSquareBracket);

            return exceptionArgument + " " + exceptionCause;
        } catch (Exception e) {
            return exception.getMessage();
        }
    }
}
