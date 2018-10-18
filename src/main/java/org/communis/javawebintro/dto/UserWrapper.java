package org.communis.javawebintro.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.communis.javawebintro.entity.User;
import org.communis.javawebintro.enums.UserRole;
import org.communis.javawebintro.enums.UserStatus;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
public class UserWrapper implements ObjectWrapper<User>, Serializable
{
    private final String EMAIL_REGEXP = "(.+@.+)";

    private Long id;

    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    @Size(max = 100)
    private String surname;

    @Size(max = 100)
    private String secondName;

    @NotNull
    @Size(max = 20)
    private String login;

    @NotNull
    @Size(max = 256)
    private String mail;

    @JsonIgnore
    @Size(min = 8, max = 20)
    private String password;

    @JsonIgnore
    @Size(min = 8, max = 20)
    private String confirmPassword;

    private Date dateLastOnline;
    private Date dateOpen;
    private Date dateClose;
    private UserStatus status;
    private UserRole role;

    private Long idLdap;

    public UserWrapper() {

    }

    public UserWrapper(User user)
    {
        toWrapper(user);
    }

    @Override
    public void toWrapper(User item)
    {
        if(item!=null)
        {
            id = item.getId();
            name = item.getName();
            surname = item.getSurname();
            secondName = item.getSecondName();
            login = item.getLogin();
            mail=item.getMail();
            password = item.getPassword();
            dateLastOnline = item.getDateLastOnline();
            dateOpen = item.getDateCreate();
            dateClose = item.getDateBlock();
            status=item.getStatus();
            role=item.getRole();
            if(item.getLdapAuth().isPresent())
                idLdap=item.getLdapAuth().get().getId();
        }
    }

    @Override
    public void fromWrapper(User item) {
        if(item!=null) {
            item.setLogin(login);
            item.setRole(role);
            item.setStatus(status);
            item.setMail(mail);
            item.setName(name);
            item.setSurname(surname);
            item.setSecondName(secondName);
        }
    }

    public String getFio() {
        return surname + " " + name + (secondName != null ? " " + secondName : "");
    }

    @AssertTrue
    public boolean isPasswordValid() {
        return (password == null && confirmPassword == null) ||
                (password != null && confirmPassword != null && password.equals(confirmPassword));
    }


    public boolean isActive(){
        return status == UserStatus.ACTIVE;
    }

    public String getEMAIL_REGEXP() {
        return EMAIL_REGEXP;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    @NotNull
    public String getSurname() {
        return surname;
    }

    public void setSurname(@NotNull String surname) {
        this.surname = surname;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @NotNull
    public String getLogin() {
        return login;
    }

    public void setLogin(@NotNull String login) {
        this.login = login;
    }

    @NotNull
    public String getMail() {
        return mail;
    }

    public void setMail(@NotNull String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Date getDateLastOnline() {
        return dateLastOnline;
    }

    public void setDateLastOnline(Date dateLastOnline) {
        this.dateLastOnline = dateLastOnline;
    }

    public Date getDateOpen() {
        return dateOpen;
    }

    public void setDateOpen(Date dateOpen) {
        this.dateOpen = dateOpen;
    }

    public Date getDateClose() {
        return dateClose;
    }

    public void setDateClose(Date dateClose) {
        this.dateClose = dateClose;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Long getIdLdap() {
        return idLdap;
    }

    public void setIdLdap(Long idLdap) {
        this.idLdap = idLdap;
    }
}