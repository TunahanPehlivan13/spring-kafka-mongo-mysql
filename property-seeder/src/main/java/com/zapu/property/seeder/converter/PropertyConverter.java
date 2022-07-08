package com.zapu.property.seeder.converter;

import com.zapu.property.seeder.controller.request.PropertyRequest;
import com.zapu.property.seeder.model.command.AddNewPropertyCommand;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class PropertyConverter implements Converter<PropertyRequest, AddNewPropertyCommand> {

    @Override
    public AddNewPropertyCommand convert(PropertyRequest source) {
        AddNewPropertyCommand command = new AddNewPropertyCommand();
        command.setCategoryId(source.getCategoryId());
        command.setCityId(source.getCityId());
        command.setPrice(source.getPrice());
        command.setCurrency(source.getCurrency());
        command.setTitle(source.getTitle());
        return command;
    }

    @Override
    public Iterable<AddNewPropertyCommand> convert(Iterable<PropertyRequest> sources) {
        return StreamSupport.stream(sources.spliterator(), false)
                .map(this::convert)
                .collect(Collectors.toUnmodifiableSet());
    }
}
