package org.communis.javawebintro.dto.filters;

import org.communis.javawebintro.enums.PilotStatus;

public class PilotFilterWrapper extends ObjectFilter {

    private String mail;
    private PilotStatus status;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public PilotStatus getStatus() {
        return status;
    }

    public void setStatus(PilotStatus status) {
        this.status = status;
    }
}
