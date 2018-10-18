package org.communis.javawebintro.dto;

import org.communis.javawebintro.entity.AircraftName;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class AircraftNameWrapper implements ObjectWrapper<AircraftName>, Serializable {

    private Long id;

    @NotNull
    @Size(max = 50)
    private String name;

    public AircraftNameWrapper(){}

    public AircraftNameWrapper(AircraftName aircraftName){
        toWrapper(aircraftName);
    }


    @Override
    public void toWrapper(AircraftName item) {
        if (item != null){
            id = item.getId();
            name = item.getName();
        }
    }

    @Override
    public void fromWrapper(AircraftName item) {
        if (item != null){
            item.setName(name);
        }
    }
}
