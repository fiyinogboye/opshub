package dev.fiyin.opshub.api;
import dev.fiyin.opshub.model.Incident;
import jakarta.validation.constraints.NotNull;
public record StatusRequest(@NotNull Incident.Status status) {}
