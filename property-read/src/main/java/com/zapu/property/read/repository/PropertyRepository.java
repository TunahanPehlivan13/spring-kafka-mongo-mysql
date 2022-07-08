package com.zapu.property.read.repository;

import com.zapu.property.read.model.Property;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PropertyRepository extends MongoRepository<Property, String> {
}
