package org.communis.javawebintro.controller.view;

import org.communis.javawebintro.dto.AircraftWrapper;
import org.communis.javawebintro.dto.filters.AircraftFilterWrapper;
import org.communis.javawebintro.enums.AircraftStatus;
import org.communis.javawebintro.exception.ServerException;
import org.communis.javawebintro.service.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/admin/aircraft")
public class AircraftController {

    private String LDAP_VIEW_PATH = "admin/aircraft/";

    private final AircraftService aircraftService;

    @Autowired
    public AircraftController(AircraftService aircraftService){
        this.aircraftService = aircraftService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView getLdapListPage(Pageable pageable, AircraftFilterWrapper filter) throws ServerException {
        ModelAndView aircraftListPage = new ModelAndView(LDAP_VIEW_PATH + "list");
        aircraftListPage.addObject("page", aircraftService.getPageByFilter(pageable, filter));
        aircraftListPage.addObject("filter", filter);
        return aircraftListPage;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add() {
        ModelAndView addPage = new ModelAndView(LDAP_VIEW_PATH + "add");
        addPage.addObject("aircraft", new AircraftWrapper());
        prepareAircraftModelAndView(addPage);
        return addPage;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") Long id) throws ServerException {
        ModelAndView addPage = new ModelAndView(LDAP_VIEW_PATH + "edit");
        addPage.addObject("aircraft", aircraftService.getForEdit(id));
        prepareAircraftModelAndView(addPage);
        return addPage;
    }

    private void prepareAircraftModelAndView(ModelAndView modelAndView) {
        modelAndView.addObject("statuses", AircraftStatus.values());
    }
}