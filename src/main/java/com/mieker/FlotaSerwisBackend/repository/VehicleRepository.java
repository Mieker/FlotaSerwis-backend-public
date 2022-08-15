package com.mieker.FlotaSerwisBackend.repository;

import com.mieker.FlotaSerwisBackend.model.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehicleRepository extends MongoRepository<Vehicle, Long> {

    Vehicle findById(String id);

    boolean existsByVin(String vin);

    boolean existsByRegistrationNumber(String registrationNumber);
}
