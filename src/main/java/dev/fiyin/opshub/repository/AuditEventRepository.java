package dev.fiyin.opshub.repository;

import dev.fiyin.opshub.model.AuditEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AuditEventRepository extends JpaRepository<AuditEvent,Long> {
    List<AuditEvent> findByIncidentIdOrderByCreatedAtDesc(Long incidentId);
}
