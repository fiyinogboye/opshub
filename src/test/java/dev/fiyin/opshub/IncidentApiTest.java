package dev.fiyin.opshub;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.fiyin.opshub.api.*;
import dev.fiyin.opshub.model.Incident;
import dev.fiyin.opshub.service.IncidentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(IncidentController.class)
@Import(dev.fiyin.opshub.config.SecurityConfig.class)
class IncidentApiTest {
 @Autowired MockMvc mvc; @Autowired ObjectMapper json; @MockitoBean IncidentService service;
 @Test void anonymousUsersAreRedirected() throws Exception {mvc.perform(get("/api/incidents")).andExpect(status().is3xxRedirection());}
 @Test void rejectsInvalidIncident() throws Exception {mvc.perform(post("/api/incidents").with(user("staff").roles("STAFF")).with(csrf()).contentType("application/json").content("{}" )).andExpect(status().isBadRequest()).andExpect(jsonPath("$.error").exists());}
 @Test void staffCannotChangeStatus() throws Exception {mvc.perform(patch("/api/incidents/1/status").with(user("staff").roles("STAFF")).with(csrf()).contentType("application/json").content(json.writeValueAsString(new StatusRequest(Incident.Status.RESOLVED)))).andExpect(status().isForbidden());verify(service,never()).updateStatus(anyLong(),any(),anyString());}
 @Test void leadCanChangeStatus() throws Exception {Incident i=new Incident();i.setStatus(Incident.Status.RESOLVED);when(service.updateStatus(1L,Incident.Status.RESOLVED,"lead")).thenReturn(i);mvc.perform(patch("/api/incidents/1/status").with(user("lead").roles("LEAD")).with(csrf()).contentType("application/json").content(json.writeValueAsString(new StatusRequest(Incident.Status.RESOLVED)))).andExpect(status().isOk()).andExpect(jsonPath("$.status").value("RESOLVED"));}
}
