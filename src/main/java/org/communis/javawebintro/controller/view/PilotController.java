package org.communis.javawebintro.controller.view;

import lombok.extern.log4j.Log4j2;
import org.communis.javawebintro.dto.PilotWrapper;
import org.communis.javawebintro.dto.filters.PilotFilterWrapper;
import org.communis.javawebintro.enums.PilotStatus;
import org.communis.javawebintro.exception.ServerException;
import org.communis.javawebintro.service.AircraftService;
import org.communis.javawebintro.service.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Log4j2
@Controller
@RequestMapping(value = "admin/pilots")
public class PilotController {
    private String USER_VIEWS_PATH = "admin/pilot/";

    private final PilotService pilotService;

    @Autowired
    public PilotController(PilotService pilotService, AircraftService aircraftService){
        this.pilotService = pilotService;
    }

    @RequestMapping(value = "")
    public ModelAndView list(Pageable pageable, PilotFilterWrapper pilotFilterWrapper) throws ServerException {
        ModelAndView resultUsersPage = new ModelAndView(USER_VIEWS_PATH + "list");
        resultUsersPage.addObject("filter", pilotFilterWrapper);
        resultUsersPage.addObject("page", pilotService.getPageByFilter(pageable, pilotFilterWrapper));
        preparePilotModelAndView(resultUsersPage);
        return resultUsersPage;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        ModelAndView addPage = new ModelAndView(USER_VIEWS_PATH + "add");
        addPage.addObject("pilot", new PilotWrapper());
        return addPage;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") Long id) throws ServerException {
        ModelAndView editPage = new ModelAndView(USER_VIEWS_PATH + "edit");
        editPage.addObject("user", pilotService.getById(id));
        preparePilotModelAndView(editPage);
        return editPage;
    }

    private void preparePilotModelAndView(ModelAndView modelAndView) {
        modelAndView.addObject("statuses", PilotStatus.values());
    }
}
