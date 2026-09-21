package dev.fiyin.opshub.service;

import dev.fiyin.opshub.api.*;
import dev.fiyin.opshub.model.*;
import dev.fiyin.opshub.repository.*;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class IncidentService {
    private final IncidentRepository incidents; private final AuditEventRepository audits;
    public IncidentService(IncidentRepository incidents, AuditEventRepository audits){this.incidents=incidents;this.audits=audits;}

    public List<Incident> search(String query, Incident.Status status, Incident.Priority priority){
        Specification<Incident> spec=(root,cq,cb)->{
            List<Predicate> p=new ArrayList<>();
            if(query!=null&&!query.isBlank()) { String q="%"+query.toLowerCase()+"%"; p.add(cb.or(cb.like(cb.lower(root.get("title")),q),cb.like(cb.lower(root.get("building")),q),cb.like(cb.lower(root.get("description")),q))); }
            if(status!=null)p.add(cb.equal(root.get("status"),status));
            if(priority!=null)p.add(cb.equal(root.get("priority"),priority));
            return cb.and(p.toArray(Predicate[]::new));
        };
        return incidents.findAll(spec,Sort.by(Sort.Direction.DESC,"createdAt"));
    }
    public Incident get(Long id){return incidents.findById(id).orElseThrow(()->new NoSuchElementException("Incident not found"));}
    @Transactional public Incident create(IncidentRequest r,String actor){
        Incident i=new Incident(); i.setTitle(r.title().trim());i.setDescription(r.description().trim());i.setBuilding(r.building().trim());i.setCategory(r.category());i.setPriority(r.priority());i.setReportedBy(actor);i.setAssignedTo(blankToNull(r.assignedTo()));
        i=incidents.save(i); audits.save(new AuditEvent(i.getId(),actor,"Created incident")); return i;
    }
    @Transactional public Incident updateStatus(Long id,Incident.Status next,String actor){
        Incident i=get(id); Incident.Status before=i.getStatus(); i.setStatus(next); i=incidents.save(i);
        audits.save(new AuditEvent(id,actor,"Changed status from "+before+" to "+next)); return i;
    }
    public List<AuditEvent> history(Long id){get(id);return audits.findByIncidentIdOrderByCreatedAtDesc(id);}
    public DashboardStats stats(){List<Incident> all=incidents.findAll();return new DashboardStats(all.size(),count(all,Incident.Status.OPEN),count(all,Incident.Status.IN_PROGRESS),all.stream().filter(i->i.getPriority()==Incident.Priority.CRITICAL&&i.getStatus()!=Incident.Status.RESOLVED).count(),count(all,Incident.Status.RESOLVED));}
    private long count(List<Incident> all,Incident.Status s){return all.stream().filter(i->i.getStatus()==s).count();}
    private String blankToNull(String s){return s==null||s.isBlank()?null:s.trim();}
}
