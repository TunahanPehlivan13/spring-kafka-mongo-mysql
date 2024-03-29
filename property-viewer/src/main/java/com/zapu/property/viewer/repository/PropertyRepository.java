package com.zapu.property.viewer.repository;

import com.zapu.property.viewer.model.PropertyDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PropertyRepository extends MongoRepository<PropertyDocument, String> {
}
