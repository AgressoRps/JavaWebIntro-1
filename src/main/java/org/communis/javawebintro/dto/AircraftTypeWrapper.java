package org.communis.javawebintro.dto;

import org.communis.javawebintro.dto.ObjectWrapper;
import org.communis.javawebintro.entity.AircraftType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class AircraftTypeWrapper implements ObjectWrapper<AircraftType>, Serializable {

    private Long id;

    @NotNull
    @Size(max = 50)
    private String name;

    public AircraftTypeWrapper(){}

    public AircraftTypeWrapper(AircraftType aircraftType){
        toWrapper(aircraftType);
    }

    @Override
    public void toWrapper(AircraftType item) {
        if (item != null){
            id = item.getId();
            name = item.getName();
        }
    }

    @Override
    public void fromWrapper(AircraftType item) {
        if (item != null){
            item.setName(name);
        }
    }
}
