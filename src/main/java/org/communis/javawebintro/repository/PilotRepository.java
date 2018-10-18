package org.communis.javawebintro.repository;

import org.communis.javawebintro.entity.Pilot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface PilotRepository extends JpaRepository<Pilot, Long>, JpaSpecificationExecutor<Pilot> {

    Optional<Pilot> findById(Long id);
}
