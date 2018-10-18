package org.communis.javawebintro.repository;

import org.communis.javawebintro.entity.AircraftType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AircraftTypeRepository extends JpaRepository<AircraftType, Long> {
}
