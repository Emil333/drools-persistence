package com.tech.drools.droolspersistence.repository;

import com.tech.drools.droolspersistence.entity.SessioninfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroolsSessionRepository extends JpaRepository<SessioninfoEntity, Long> {
}
