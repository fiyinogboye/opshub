package dev.fiyin.opshub;

import dev.fiyin.opshub.api.IncidentRequest;
import dev.fiyin.opshub.model.Incident;
import dev.fiyin.opshub.repository.*;
import dev.fiyin.opshub.service.IncidentService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class IncidentServiceTest {
 @Mock IncidentRepository incidents; @Mock AuditEventRepository audits; @InjectMocks IncidentService service;
 @Test void createsIncidentWithAuthenticatedReporter(){
  when(incidents.save(any())).thenAnswer(x->{Incident i=x.getArgument(0);setId(i,42L);return i;});
  Incident created=service.create(new IncidentRequest("Broken reader","Card reader flashes red","Hedden",Incident.Category.ACCESS,Incident.Priority.HIGH,"IT"),"fiyin");
  assertThat(created.getReportedBy()).isEqualTo("fiyin");assertThat(created.getStatus()).isEqualTo(Incident.Status.OPEN);verify(audits).save(argThat(a->a.getIncidentId().equals(42L)&&a.getAction().contains("Created")));
 }
 private void setId(Incident i,Long id){try{var f=Incident.class.getDeclaredField("id");f.setAccessible(true);f.set(i,id);}catch(Exception e){throw new RuntimeException(e);}}
}
