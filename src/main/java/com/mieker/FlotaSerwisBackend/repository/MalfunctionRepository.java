package com.mieker.FlotaSerwisBackend.repository;

import com.mieker.FlotaSerwisBackend.model.Malfunction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MalfunctionRepository extends MongoRepository<Malfunction, Long> {

    Malfunction findById(String id);
}
