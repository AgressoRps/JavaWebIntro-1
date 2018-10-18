package org.communis.javawebintro.dto;


import org.communis.javawebintro.entity.Company;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class CompanyWrapper implements ObjectWrapper<Company>, Serializable {

    private Long id;

    @NotNull
    @Size(max = 50)
    private String name;

    public CompanyWrapper(){}

    public CompanyWrapper(Company company){
        toWrapper(company);
    }

    @Override
    public void toWrapper(Company item) {
        if (item != null){
            id = item.getId();
            name = item.getName();
        }
    }

    @Override
    public void fromWrapper(Company item) {
        if (item != null){
            item.setName(name);
        }
    }
}
