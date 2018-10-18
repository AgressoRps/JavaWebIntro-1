package org.communis.javawebintro.repository.specifications;

import org.communis.javawebintro.dto.AircraftWrapper;
import org.communis.javawebintro.dto.filters.AircraftFilterWrapper;
import org.communis.javawebintro.entity.Aircraft;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public abstract class AircraftSpecification implements Specification<Aircraft> {

    private AircraftSpecification(){}

    public static AircraftSpecification build(final AircraftFilterWrapper filter){
        return new AircraftSpecification() {
            @Override
            public Predicate toPredicate(Root<Aircraft> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                final List predicates = new ArrayList();

                if (filter != null)
                {
                    if (filter.getSearch() != null && !filter.getSearch().isEmpty())
                    {
                        predicates.add(cb.or(
                                cb.like(cb.upper(root.get("type")), '%' + filter.getSearch().toUpperCase() + '%'),
                                cb.like(cb.upper(root.get("name")), '%' + filter.getSearch().toUpperCase() + '%'),
                                cb.like(cb.upper(root.get("company")), '%' + filter.getSearch().toUpperCase() + '%'),
                                cb.like(cb.upper(root.get("pilot")), '%' + filter.getSearch().toUpperCase() + '%')));
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
