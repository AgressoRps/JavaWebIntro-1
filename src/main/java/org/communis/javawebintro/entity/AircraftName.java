package org.communis.javawebintro.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "name_aircraft")
public class AircraftName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public AircraftName(String name){
        this.name = name;
    }

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
}
