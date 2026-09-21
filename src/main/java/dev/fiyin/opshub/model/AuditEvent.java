package dev.fiyin.opshub.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity @Table(name="audit_events")
public class AuditEvent {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false) private Long incidentId;
    @Column(nullable=false, length=80) private String actor;
    @Column(nullable=false, length=500) private String action;
    @Column(nullable=false, updatable=false) private Instant createdAt;
    protected AuditEvent() {}
    public AuditEvent(Long incidentId,String actor,String action){this.incidentId=incidentId;this.actor=actor;this.action=action;this.createdAt=Instant.now();}
    public Long getId(){return id;} public Long getIncidentId(){return incidentId;} public String getActor(){return actor;} public String getAction(){return action;} public Instant getCreatedAt(){return createdAt;}
}
