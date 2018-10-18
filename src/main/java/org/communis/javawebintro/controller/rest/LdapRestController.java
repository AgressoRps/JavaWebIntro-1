package org.communis.javawebintro.controller.rest;

import org.communis.javawebintro.dto.LdapAuthWrapper;
import org.communis.javawebintro.dto.LdapGroupInfo;
import org.communis.javawebintro.exception.ServerException;
import org.communis.javawebintro.service.LdapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/admin/ldap")
public class LdapRestController {

    private final LdapService ldapService;

    @Autowired
    public LdapRestController(LdapService ldapService) {
        this.ldapService = ldapService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void add(LdapAuthWrapper ldapAuth, HttpServletResponse response) throws ServerException, IOException {
        ldapService.add(ldapAuth);
        response.sendRedirect("/admin/ldap");
    }

    @RequestMapping(value = "", method = RequestMethod.PATCH)
    public void edit(LdapAuthWrapper ldapAuth, HttpServletResponse response) throws ServerException, IOException {
        System.out.println(ldapAuth);
        ldapService.edit(ldapAuth);
        response.sendRedirect("/admin/ldap");
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<LdapAuthWrapper> getAll() throws ServerException {
        return ldapService.getAllActive();
    }

    @RequestMapping(value = "/groups", method = RequestMethod.POST)
    public List<String> getGroups(LdapAuthWrapper wrapper) throws ServerException {
        return ldapService.getGroups(wrapper);
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public List<LdapGroupInfo> testConnection(LdapAuthWrapper wrapper) throws ServerException {
        return ldapService.getGroupsInfo(wrapper);
    }

    @RequestMapping(value = "/{id}/activate", method = RequestMethod.POST)
    public void activate(@PathVariable("id") Long id) throws ServerException, IOException {
        ldapService.activate(id);
    }

    @RequestMapping(value = "/{id}/deactivate", method = RequestMethod.POST)
    public void deactivate(@PathVariable("id") Long id) throws ServerException, IOException {
        ldapService.deactivate(id);
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    public void deleteLdap(@PathVariable("id") Long id) throws ServerException, IOException{
        ldapService.delete(id);
    }
}
