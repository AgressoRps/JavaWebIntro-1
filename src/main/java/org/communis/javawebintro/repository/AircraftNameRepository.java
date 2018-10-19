package org.communis.javawebintro.repository;

import org.communis.javawebintro.entity.AircraftName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AircraftNameRepository extends JpaRepository<AircraftName, Long>, JpaSpecificationExecutor<AircraftName> {
}
