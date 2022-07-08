package com.zapu.property.viewer.repository;

import com.zapu.property.viewer.model.Property;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PropertyRepository extends MongoRepository<Property, String> {
}
