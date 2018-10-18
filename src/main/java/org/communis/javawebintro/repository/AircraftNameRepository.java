package org.communis.javawebintro.repository;

import org.communis.javawebintro.entity.AircraftName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AircraftNameRepository extends JpaRepository<AircraftName, Long> {
}
