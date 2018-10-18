package org.communis.javawebintro.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "ldap_auth")
public class LdapAuth 
{  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    
    @Column(name = "date_open")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOpen;
    
    @Column
    private String address;

    @Column
    private String port;

    @Column
    private String name;

    @Column(name = "groups_directory")
    private String groupsDirectory;

    @Column(name = "users_directory")
    private String usersDirectory;
    
    @Column(name = "active")
    private Boolean active;

    @Column(name = "role_from_group")
    private Boolean roleFromGroup;

    @Column(name = "role_references")
    private String rolesReferences;

    @Column(name = "domain")
    private String domain;

    @Column(name = "group_class")
    private String groupClass;

    @Column(name = "user_class")
    private String userClass;

    @Column(name = "ldap_login")
    private String ldapLogin;

    @Column(name = "ldap_password")
    private String ldapPassword;

    @Column(name = "credentials_auth")
    private boolean credentialsAuth;

    @Column
    private boolean readonly;

    @JoinColumn(name = "user_attributes")
    @OneToOne
    private LdapUserAttributes userAttributes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateOpen() {
        return dateOpen;
    }

    public void setDateOpen(Date dateOpen) {
        this.dateOpen = dateOpen;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupsDirectory() {
        return groupsDirectory;
    }

    public void setGroupsDirectory(String groupsDirectory) {
        this.groupsDirectory = groupsDirectory;
    }

    public String getUsersDirectory() {
        return usersDirectory;
    }

    public void setUsersDirectory(String usersDirectory) {
        this.usersDirectory = usersDirectory;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getRoleFromGroup() {
        return roleFromGroup;
    }

    public void setRoleFromGroup(Boolean roleFromGroup) {
        this.roleFromGroup = roleFromGroup;
    }

    public String getRolesReferences() {
        return rolesReferences;
    }

    public void setRolesReferences(String rolesReferences) {
        this.rolesReferences = rolesReferences;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getGroupClass() {
        return groupClass;
    }

    public void setGroupClass(String groupClass) {
        this.groupClass = groupClass;
    }

    public String getUserClass() {
        return userClass;
    }

    public void setUserClass(String userClass) {
        this.userClass = userClass;
    }

    public String getLdapLogin() {
        return ldapLogin;
    }

    public void setLdapLogin(String ldapLogin) {
        this.ldapLogin = ldapLogin;
    }

    public String getLdapPassword() {
        return ldapPassword;
    }

    public void setLdapPassword(String ldapPassword) {
        this.ldapPassword = ldapPassword;
    }

    public boolean isCredentialsAuth() {
        return credentialsAuth;
    }

    public void setCredentialsAuth(boolean credentialsAuth) {
        this.credentialsAuth = credentialsAuth;
    }

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }

    public LdapUserAttributes getUserAttributes() {
        return userAttributes;
    }

    public void setUserAttributes(LdapUserAttributes userAttributes) {
        this.userAttributes = userAttributes;
    }
}
