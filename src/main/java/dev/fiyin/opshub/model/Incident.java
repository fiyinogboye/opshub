package dev.fiyin.opshub.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "incidents")
public class Incident {
    public enum Category { ACCESS, FACILITIES, SAFETY, TECHNOLOGY, GUEST_SUPPORT, OTHER }
    public enum Priority { LOW, MEDIUM, HIGH, CRITICAL }
    public enum Status { OPEN, IN_PROGRESS, WAITING, RESOLVED }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 120) private String title;
    @Column(nullable = false, length = 2000) private String description;
    @Column(nullable = false, length = 80) private String building;
    @Enumerated(EnumType.STRING) @Column(nullable = false) private Category category;
    @Enumerated(EnumType.STRING) @Column(nullable = false) private Priority priority;
    @Enumerated(EnumType.STRING) @Column(nullable = false) private Status status = Status.OPEN;
    @Column(nullable = false, length = 80) private String reportedBy;
    @Column(length = 80) private String assignedTo;
    @Column(nullable = false, updatable = false) private Instant createdAt;
    @Column(nullable = false) private Instant updatedAt;

    @PrePersist void onCreate() { createdAt = updatedAt = Instant.now(); }
    @PreUpdate void onUpdate() { updatedAt = Instant.now(); }
    public Long getId(){return id;} public String getTitle(){return title;} public void setTitle(String v){title=v;}
    public String getDescription(){return description;} public void setDescription(String v){description=v;}
    public String getBuilding(){return building;} public void setBuilding(String v){building=v;}
    public Category getCategory(){return category;} public void setCategory(Category v){category=v;}
    public Priority getPriority(){return priority;} public void setPriority(Priority v){priority=v;}
    public Status getStatus(){return status;} public void setStatus(Status v){status=v;}
    public String getReportedBy(){return reportedBy;} public void setReportedBy(String v){reportedBy=v;}
    public String getAssignedTo(){return assignedTo;} public void setAssignedTo(String v){assignedTo=v;}
    public Instant getCreatedAt(){return createdAt;} public Instant getUpdatedAt(){return updatedAt;}
}
