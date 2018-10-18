package org.communis.javawebintro.entity;

import lombok.Data;
import org.communis.javawebintro.enums.AircraftStatus;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "aircraft")
public class Aircraft {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_name")
    private AircraftName aircraftName;

    @Column(name = "places")
    private Integer countPlaces;

    @Enumerated(EnumType.STRING)
    @Column(name = "condition_air")
    private AircraftStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type")
    private AircraftType aircraftType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_company")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pilot ")
    private Pilot pilot;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AircraftName getAircraftName() {
        return aircraftName;
    }

    public void setAircraftName(AircraftName aircraftName) {
        this.aircraftName = aircraftName;
    }

    public Integer getCountPlaces() {
        return countPlaces;
    }

    public void setCountPlaces(Integer countPlaces) {
        this.countPlaces = countPlaces;
    }

    public AircraftStatus getStatus() {
        return status;
    }

    public void setStatus(AircraftStatus status) {
        this.status = status;
    }

    public AircraftType getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(AircraftType aircraftType) {
        this.aircraftType = aircraftType;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }
}
