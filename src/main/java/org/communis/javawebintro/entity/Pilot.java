package org.communis.javawebintro.entity;

import lombok.Data;
import org.communis.javawebintro.enums.PilotStatus;

import javax.persistence.*;

@Data
@Entity
@Table(name = "pilot")
public class Pilot {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "mail")
    private String mail;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PilotStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

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
