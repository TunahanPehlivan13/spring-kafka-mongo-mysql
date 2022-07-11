package com.zapu.property.store.executor.impl;

import com.zapu.property.store.converter.PropertyConverter;
import com.zapu.property.store.handler.impl.ProducePropertyAddedEventFlowHandler;
import com.zapu.property.store.handler.impl.WritePropertyToMongoFlowHandler;
import com.zapu.property.store.model.PropertyDocument;
import com.zapu.property.store.model.command.AddNewPropertyCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PropertyStoreRollbackSupportsFlowExecutorTest {

    @InjectMocks
    private PropertyStoreRollbackSupportsFlowExecutor executor;

    @Mock
    private PropertyConverter propertyConverter;

    @Mock
    private WritePropertyToMongoFlowHandler writePropertyToMongoFlowHandler;

    @Mock
    private ProducePropertyAddedEventFlowHandler producePropertyAddedEventFlowHandler;

    @Test
    void shouldExecuteAllHandlersWhenNotThrownAnException() {
        PropertyDocument property = new PropertyDocument();
        AddNewPropertyCommand command = new AddNewPropertyCommand();

        when(propertyConverter.convert(command)).thenReturn(property);

        executor.execute(command);

        verify(writePropertyToMongoFlowHandler).handle(property);
        verify(producePropertyAddedEventFlowHandler).handle(property);
    }

    @Test
    void shouldExecuteAllFailoversWhenAnyOfFlowExecutorThrowsAnException() {
        AddNewPropertyCommand command = new AddNewPropertyCommand();
        PropertyDocument property = new PropertyDocument();

        when(propertyConverter.convert(command)).thenReturn(property);
        doThrow(RuntimeException.class).when(writePropertyToMongoFlowHandler).handle(property);

        executor.execute(command);

        verify(writePropertyToMongoFlowHandler).handle(property);

        verify(writePropertyToMongoFlowHandler).doFailover(property);
        verify(producePropertyAddedEventFlowHandler).doFailover(property);
    }
}