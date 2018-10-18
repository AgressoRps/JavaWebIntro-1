package org.communis.javawebintro.repository;

import org.communis.javawebintro.entity.Aircraft;
import org.communis.javawebintro.enums.AircraftStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface AircraftRepository extends JpaRepository<Aircraft, Long>, JpaSpecificationExecutor<Aircraft> {

    List<Aircraft> findByStatus(AircraftStatus status);
    Optional<Aircraft> findById(Long id);
}
