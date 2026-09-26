package com.gateway.security_gateway.repository;

import com.gateway.security_gateway.model.DecisionLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DecisionLogRepository extends JpaRepository<DecisionLog, Long> {
}
