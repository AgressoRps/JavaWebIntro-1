package org.communis.javawebintro.controller.rest;

import org.communis.javawebintro.dto.PilotWrapper;
import org.communis.javawebintro.exception.InvalidDataException;
import org.communis.javawebintro.exception.NotFoundException;
import org.communis.javawebintro.exception.ServerException;
import org.communis.javawebintro.exception.error.ErrorCodeConstants;
import org.communis.javawebintro.exception.error.ErrorInformationBuilder;
import org.communis.javawebintro.service.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping(value = "admin/pilots")
public class PilotRestController {

    private final PilotService pilotService;

    @Autowired
    public PilotRestController(PilotService pilotService) {
        this.pilotService = pilotService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void add(@Valid PilotWrapper pilotWrapper, BindingResult bindingResult) throws InvalidDataException, ServerException {
        if (bindingResult.hasErrors()) {
            throw new InvalidDataException(ErrorInformationBuilder.build(ErrorCodeConstants.DATA_VALIDATE_ERROR));
        }
        pilotService.add(pilotWrapper);
    }

    @RequestMapping(value = "", method = RequestMethod.PATCH)
    public void editPersonal(@Valid PilotWrapper pilotWrapper, BindingResult bindingResult,
                             HttpServletResponse response)
            throws InvalidDataException, NotFoundException, ServerException, IOException {
        if (bindingResult.hasErrors()) {
            throw new InvalidDataException(ErrorInformationBuilder.build(ErrorCodeConstants.DATA_VALIDATE_ERROR));
        }
        pilotService.edit(pilotWrapper);
        response.sendRedirect("/admin/pilots/");
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addUser(@Valid PilotWrapper pilotWrapper, BindingResult bindingResult,
                        HttpServletResponse response)
            throws InvalidDataException, NotFoundException, ServerException, IOException {
        if (bindingResult.hasErrors()) {
            throw new InvalidDataException(ErrorInformationBuilder.build(ErrorCodeConstants.DATA_VALIDATE_ERROR));
        }
        pilotService.add(pilotWrapper);
        response.sendRedirect("/admin/pilots");
    }

    @RequestMapping(value = "/{id}/FLIGHT", method = RequestMethod.POST)
    public void flight(@PathVariable("id") Long id)
            throws InvalidDataException, ServerException, NotFoundException {
        pilotService.flight(id);
    }

    @RequestMapping(value = "/{id}/available", method = RequestMethod.POST)
    public void available(@PathVariable("id") Long id) throws NotFoundException, ServerException {
        pilotService.available(id);
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") Long id) throws NotFoundException, ServerException{
        pilotService.delete(id);
    }
}
