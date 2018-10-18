package org.communis.javawebintro.enums;

public enum AircraftStatus {
    AVAILABLE,
    REPAIR;

    public String getStringName() {
        switch(this) {
            case AVAILABLE:
                return "Доступен";
            case REPAIR:
                return "На ремонте";
            default:
                return null;
        }
    }
}
