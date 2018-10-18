package org.communis.javawebintro.enums;

public enum PilotStatus {
    AVAILABLE,
    FLIGHT;


    public String getStringName() {
        switch(this) {
            case AVAILABLE:
                System.out.println("hi");
                return "Доступен";
            case FLIGHT:
                System.out.println("hi2");
                return "В рейсе";
            default:
                System.out.println("hi3");
                return null;
        }
    }
}
