package org.communis.javawebintro.controller.rest;


import org.communis.javawebintro.dto.AircraftWrapper;
import org.communis.javawebintro.exception.ServerException;
import org.communis.javawebintro.service.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/admin/aircraft")
public class AircraftRestController {

    private final AircraftService aircraftService;

    @Autowired
    public AircraftRestController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void add(AircraftWrapper aircraftWrapper, HttpServletResponse response)
            throws ServerException, IOException {
        aircraftService.add(aircraftWrapper);
        response.sendRedirect("/admin/aircraft");
    }

    @RequestMapping(value = "", method = RequestMethod.PATCH)
    public void edit(AircraftWrapper aircraftWrapper, HttpServletResponse response) throws ServerException, IOException {
        System.out.println(aircraftWrapper);
        aircraftService.edit(aircraftWrapper);
        response.sendRedirect("/admin/aircraft");
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<AircraftWrapper> getAll() throws ServerException {
        return aircraftService.getAllAvailable();
    }

    /*@RequestMapping(value = "/groups", method = RequestMethod.POST)
    public List<String> getGroups(AircraftWrapper wrapper) throws ServerException {
        return aircraftService.getGroups(wrapper);
    }*/

    @RequestMapping(value = "/{id}/available", method = RequestMethod.POST)
    public void available(@PathVariable("id") Long id) throws ServerException, IOException {
        aircraftService.available(id);
    }

    @RequestMapping(value = "/{id}/repair", method = RequestMethod.POST)
    public void repair(@PathVariable("id") Long id) throws ServerException, IOException {
        aircraftService.repair(id);
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    public void deleteAircraft(@PathVariable("id") Long id) throws ServerException, IOException{
        aircraftService.delete(id);
    }
}
