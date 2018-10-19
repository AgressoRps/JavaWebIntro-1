package org.communis.javawebintro.dto;

import org.communis.javawebintro.dto.ObjectWrapper;
import org.communis.javawebintro.entity.*;
import org.communis.javawebintro.enums.AircraftStatus;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class AircraftWrapper implements ObjectWrapper<Aircraft>, Serializable {

    private Long id;
    private AircraftNameWrapper aircraftName = new AircraftNameWrapper();

    @NotNull
    @Size(max = 10)
    private Integer countPlaces;

    private AircraftStatus status;
    private AircraftTypeWrapper aircraftType = new AircraftTypeWrapper();
    private CompanyWrapper company = new CompanyWrapper();
    private PilotWrapper pilot = new PilotWrapper();

    public AircraftWrapper(){

    }

    public AircraftWrapper(Aircraft aircraft){
        toWrapper(aircraft);
    }

    @Override
    public void toWrapper(Aircraft item) {
        if(item!=null)
        {
            id = item.getId();
            aircraftName = new AircraftNameWrapper(item.getAircraftName());
            countPlaces = item.getCountPlaces();
            status = item.getStatus();
            aircraftType = new AircraftTypeWrapper(item.getAircraftType());
            company = new CompanyWrapper(item.getCompany());
            pilot = new PilotWrapper(item.getPilot());
        }
    }

    @Override
    public void fromWrapper(Aircraft item) {
        if(item!=null) {
            item.setId(id);
            item.setCountPlaces(countPlaces);
            item.setStatus(status);

            AircraftName aircraftNameAttr = new AircraftName();
            aircraftName.fromWrapper(aircraftNameAttr);
            item.setAircraftName(aircraftNameAttr);

            AircraftType aircraftTypeAttr = new AircraftType();
            aircraftType.fromWrapper(aircraftTypeAttr);
            item.setAircraftType(aircraftTypeAttr);

            Company companyAttr = new Company();
            company.fromWrapper(companyAttr);
            item.setCompany(companyAttr);

            Pilot pilotAttr = new Pilot();
            pilot.fromWrapper(pilotAttr);
            item.setPilot(pilotAttr);
        }
    }

    public boolean isAvailable(){
        return status == AircraftStatus.AVAILABLE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AircraftNameWrapper getAircraftName() {
        return aircraftName;
    }

    public void setAircraftName(Long id, String name) {
        AircraftNameWrapper wrapper = new AircraftNameWrapper();
        wrapper.setId(id);
        wrapper.setName(name);
        aircraftName = wrapper;
    }

    public void setAircraftType(Long id, String name) {
        AircraftTypeWrapper wrapper = new AircraftTypeWrapper();
        wrapper.setId(id);
        wrapper.setName(name);
        aircraftType = wrapper;
    }

    @NotNull
    public Integer getCountPlaces() {
        return countPlaces;
    }

    public void setCountPlaces(@NotNull Integer countPlaces) {
        this.countPlaces = countPlaces;
    }

    public AircraftStatus getStatus() {
        return status;
    }

    public void setStatus(AircraftStatus status) {
        this.status = status;
    }

    public AircraftTypeWrapper getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(AircraftTypeWrapper aircraftType) {
        this.aircraftType = aircraftType;
    }

    public CompanyWrapper getCompany() {
        return company;
    }

    public void setCompany(CompanyWrapper company) {
        this.company = company;
    }

    public PilotWrapper getPilot() {
        return pilot;
    }

    public void setPilot(PilotWrapper pilot) {
        this.pilot = pilot;
    }
}
