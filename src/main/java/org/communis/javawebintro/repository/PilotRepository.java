package org.communis.javawebintro.repository;

import org.communis.javawebintro.entity.Pilot;
import org.communis.javawebintro.enums.PilotStatus;
import org.communis.javawebintro.service.PilotService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface PilotRepository extends JpaRepository<Pilot, Long>, JpaSpecificationExecutor<Pilot> {

    Optional<Pilot> findFirstByMail(String mail);
    Optional<Pilot> findById(Long id);
    List<Pilot> findByStatus(PilotStatus status);
}
