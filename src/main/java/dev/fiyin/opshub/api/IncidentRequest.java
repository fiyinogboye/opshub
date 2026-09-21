package dev.fiyin.opshub.api;

import dev.fiyin.opshub.model.Incident;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record IncidentRequest(
    @NotBlank @Size(max=120) String title,
    @NotBlank @Size(max=2000) String description,
    @NotBlank @Size(max=80) String building,
    @NotNull Incident.Category category,
    @NotNull Incident.Priority priority,
    @Size(max=80) String assignedTo) {}
