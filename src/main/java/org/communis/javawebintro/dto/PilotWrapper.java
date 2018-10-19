package org.communis.javawebintro.dto;

import org.communis.javawebintro.entity.Pilot;
import org.communis.javawebintro.enums.PilotStatus;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class PilotWrapper implements ObjectWrapper<Pilot>, Serializable {

    private final String EMAIL_REGEXP = "(.+@.+)";

    private Long id;

    @NotNull
    @Size(max = 50)
    private String name;

    @NotNull
    @Size(max = 50)
    private String surname;

    @NotNull
    @Size(max = 50)
    private String secondName;

    @NotNull
    @Size(max = 50)
    private String mail;

    private PilotStatus status;

    public PilotWrapper(){

    }

    public PilotWrapper(Pilot pilot){
        toWrapper(pilot);
    }

    @Override
    public void toWrapper(Pilot item) {
        if(item!=null)
        {
            id = item.getId();
            name = item.getName();
            surname = item.getSurname();
            secondName = item.getSecondName();
            mail = item.getMail();
            status = item.getStatus();
        }
    }

    @Override
    public void fromWrapper(Pilot item) {
        if(item!=null)
        {
            item.setId(id);
            item.setName(name);
            item.setSurname(surname);
            item.setSecondName(secondName);
            item.setMail(mail);
            item.setStatus(status);
        }
    }

    public String getFio() {
        return surname + " " + name + secondName;
    }
    public boolean isAvailable(){
        return status == PilotStatus.AVAILABLE;
    }

    public String getEMAIL_REGEXP() {
        return EMAIL_REGEXP;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    @NotNull
    public String getSurname() {
        return surname;
    }

    public void setSurname(@NotNull String surname) {
        this.surname = surname;
    }

    @NotNull
    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(@NotNull String secondName) {
        this.secondName = secondName;
    }

    @NotNull
    public String getMail() {
        return mail;
    }

    public void setMail(@NotNull String mail) {
        this.mail = mail;
    }

    public PilotStatus getStatus() {
        return status;
    }

    public void setStatus(PilotStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return surname + " " + name + " " + secondName;
    }
}
