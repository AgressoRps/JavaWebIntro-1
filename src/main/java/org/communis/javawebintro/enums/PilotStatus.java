package org.communis.javawebintro.enums;

public enum PilotStatus {
    AVAILABLE,
    FLIGHT,
    HOLIDAY;


    public String getStringName() {
        switch(this) {
            case AVAILABLE:
                return "Доступен";
            case FLIGHT:
                return "В рейсе";
            case HOLIDAY:
                return "В отпуске";
            default:
                return null;
        }
    }
}
