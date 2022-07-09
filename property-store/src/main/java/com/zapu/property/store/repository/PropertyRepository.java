package com.zapu.property.store.repository;

import com.zapu.property.store.model.PropertyDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PropertyRepository extends MongoRepository<PropertyDocument, String> {
}
