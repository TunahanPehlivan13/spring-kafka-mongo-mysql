package com.zapu.property.store.model.event;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class NewPropertyAddedEvent implements Serializable {
    private static final long serialVersionUID = -5454250511614193639L;

    private String id;
}
