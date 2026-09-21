package dev.fiyin.opshub.config;
import dev.fiyin.opshub.api.IncidentRequest;
import dev.fiyin.opshub.model.Incident;
import dev.fiyin.opshub.repository.IncidentRepository;
import dev.fiyin.opshub.service.IncidentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;

@Configuration public class DemoDataConfig {
 @Bean @Profile("!prod") CommandLineRunner seed(IncidentRepository repo,IncidentService service){return args->{if(repo.count()==0){
  service.create(new IncidentRequest("Suite door access card failing","Resident cannot access suite 623A with newly issued card.","North Quad",Incident.Category.ACCESS,Incident.Priority.HIGH,"Facilities"),"system");
  Incident printer=service.create(new IncidentRequest("Printer drum reached end of life","Print and copy services are unavailable in the service centre.","Commons",Incident.Category.TECHNOLOGY,Incident.Priority.MEDIUM,"Media Services"),"system");
  service.updateStatus(printer.getId(),Incident.Status.IN_PROGRESS,"system");
  service.create(new IncidentRequest("Guest room change support","Guest belongings were delivered to the original room after reassignment.","Matthews Hall",Incident.Category.GUEST_SUPPORT,Incident.Priority.LOW,"Guest Services"),"system");
 }};}
}
