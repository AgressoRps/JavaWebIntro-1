package org.communis.javawebintro.repository.specifications;

import org.communis.javawebintro.dto.filters.PilotFilterWrapper;
import org.communis.javawebintro.entity.Pilot;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public abstract class PilotSpecification implements Specification<Pilot> {

    private PilotSpecification(){}

    public static PilotSpecification build(final PilotFilterWrapper filter){
        return new PilotSpecification() {
            @Override
            public Predicate toPredicate(Root<Pilot> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                final List predicates = new ArrayList();

                if (filter != null)
                {
                    if (filter.getSearch() != null && !filter.getSearch().isEmpty())
                    {
                        predicates.add(cb.or(
                                cb.like(cb.upper(root.get("mail")), '%' + filter.getSearch().toUpperCase() + '%'),
                                cb.like(cb.upper(root.get("name")), '%' + filter.getSearch().toUpperCase() + '%'),
                                cb.like(cb.upper(root.get("surname")), '%' + filter.getSearch().toUpperCase() + '%'),
                                cb.like(cb.upper(root.get("secondName")), '%' + filter.getSearch().toUpperCase() + '%')));
                    }
                    if (filter.getStatus() != null) {
                        predicates.add(cb.equal(root.get("status"), filter.getStatus()));
                    }

                }
                return cb.and((Predicate[]) predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

}
