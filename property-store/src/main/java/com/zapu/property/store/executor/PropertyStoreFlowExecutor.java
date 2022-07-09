package com.zapu.property.store.executor;

import com.zapu.property.store.model.command.AddNewPropertyCommand;

public interface PropertyStoreFlowExecutor {

    void execute(AddNewPropertyCommand addNewPropertyCommand);
}
