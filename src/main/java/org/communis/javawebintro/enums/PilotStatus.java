package org.communis.javawebintro.enums;

public enum PilotStatus {
    AVAILABLE,
    FLIGHT;


    public String getStringName() {
        switch(this) {
            case AVAILABLE:
                return "Доступен";
            case FLIGHT:
                return "В рейсе";
            default:
                return null;
        }
    }
}
