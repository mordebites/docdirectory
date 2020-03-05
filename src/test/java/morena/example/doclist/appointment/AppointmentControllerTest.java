package morena.example.doclist.appointment;

import com.fasterxml.jackson.databind.ObjectMapper;
import morena.example.doclist.BaseControllerTest;
import morena.example.doclist.controller.AppointmentController;
import morena.example.doclist.domain.Address;
import morena.example.doclist.domain.Appointment;
import morena.example.doclist.domain.Doctor;
import morena.example.doclist.domain.Practice;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Date;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Matchers.*;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(AppointmentController.class)
class AppointmentControllerTest extends BaseControllerTest {

    @Test
    public void createAppointment() throws Exception {
        Address desiresAddress = new Address("Karl 1", "Mitte", "Berlin", "Germany", "10178");
        Practice desiredPractice = new Practice("BestDoc", desiresAddress);
        Doctor desiredDoctor = new Doctor("Morena De Liddo", "English", "GP", desiredPractice);
        Appointment newAppointment = new Appointment("Donald Duck", new Date(), desiredDoctor);
        Appointment appointmentWithId = new Appointment("Donald Duck", new Date(), desiredDoctor);
        appointmentWithId.setId(1);
        when(appointmentService.addAppointment(any(Appointment.class))).thenReturn(appointmentWithId);

        mockMvc.perform(post("/appointments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(newAppointment))
                .characterEncoding("utf-8"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", containsString("http://localhost/appointments/")));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}