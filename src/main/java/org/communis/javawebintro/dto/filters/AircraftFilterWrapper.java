package org.communis.javawebintro.dto.filters;

import org.communis.javawebintro.enums.AircraftStatus;

public class AircraftFilterWrapper extends ObjectFilter {

    private AircraftStatus status;

    public AircraftStatus getStatus() {
        return status;
    }

    public void setStatus(AircraftStatus status) {
        this.status = status;
    }
}
