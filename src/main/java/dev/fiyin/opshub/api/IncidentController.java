package dev.fiyin.opshub.api;

import dev.fiyin.opshub.model.*;
import dev.fiyin.opshub.service.IncidentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api")
public class IncidentController {
    private final IncidentService service;
    public IncidentController(IncidentService service){this.service=service;}
    @GetMapping("/incidents") public List<Incident> list(@RequestParam(required=false)String query,@RequestParam(required=false)Incident.Status status,@RequestParam(required=false)Incident.Priority priority){return service.search(query,status,priority);}
    @GetMapping("/incidents/{id}") public Incident get(@PathVariable Long id){return service.get(id);}
    @PostMapping("/incidents") @ResponseStatus(HttpStatus.CREATED) public Incident create(@Valid @RequestBody IncidentRequest r,Authentication a){return service.create(r,a.getName());}
    @PatchMapping("/incidents/{id}/status") @PreAuthorize("hasAnyRole('LEAD','ADMIN')") public Incident status(@PathVariable Long id,@Valid @RequestBody StatusRequest r,Authentication a){return service.updateStatus(id,r.status(),a.getName());}
    @GetMapping("/incidents/{id}/history") public List<AuditEvent> history(@PathVariable Long id){return service.history(id);}
    @GetMapping("/dashboard") public DashboardStats stats(){return service.stats();}
}
