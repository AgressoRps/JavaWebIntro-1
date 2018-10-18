package org.communis.javawebintro.dto;

import org.communis.javawebintro.dto.ObjectWrapper;
import org.communis.javawebintro.entity.Aircraft;
import org.communis.javawebintro.enums.AircraftStatus;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class AircraftWrapper implements ObjectWrapper<Aircraft>, Serializable {

    private Long id;
    private Long idAircraftName;

    @NotNull
    @Size(max = 10)
    private Integer countPlaces;

    private AircraftStatus status;
    private Long idAircraftType;
    private Long idCompany;
    private Long idPilot;

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
            idAircraftName = item.getAircraftName().getId();
            countPlaces = item.getCountPlaces();
            status = item.getStatus();
            idAircraftType = item.getAircraftType().getId();
            idCompany = item.getCompany().getId();
            idPilot = item.getPilot().getId();
        }
    }

    @Override
    public void fromWrapper(Aircraft item) {
        if(item!=null) {
            item.setCountPlaces(countPlaces);
            item.setStatus(status);
        }
    }

    public boolean isAvailable(){
        return status == AircraftStatus.AVAILABLE;
    }

    public String getStatus(){
        return status.getStringName();
    }

    public Integer getCountPlaces(){
        return countPlaces;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdAircraftName() {
        return idAircraftName;
    }

    public void setIdAircraftName(long idAircraftName) {
        this.idAircraftName = idAircraftName;
    }

    public void setCountPlaces(@NotNull Integer countPlaces) {
        this.countPlaces = countPlaces;
    }

    public void setStatus(AircraftStatus status) {
        this.status = status;
    }

    public long getIdAircraftType() {
        return idAircraftType;
    }

    public void setIdAircraftType(long idAircraftType) {
        this.idAircraftType = idAircraftType;
    }

    public long getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(long idCompany) {
        this.idCompany = idCompany;
    }

    public long getIdPilot() {
        return idPilot;
    }

    public void setIdPilot(long idPilot) {
        this.idPilot = idPilot;
    }
}
