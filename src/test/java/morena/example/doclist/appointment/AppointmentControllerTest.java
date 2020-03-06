package morena.example.doclist.appointment;

import com.fasterxml.jackson.databind.ObjectMapper;
import morena.example.doclist.BaseControllerTest;
import morena.example.doclist.controller.AppointmentController;
import morena.example.doclist.domain.Address;
import morena.example.doclist.domain.Appointment;
import morena.example.doclist.domain.Doctor;
import morena.example.doclist.domain.Practice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(AppointmentController.class)
class AppointmentControllerTest extends BaseControllerTest {

    @Test
    public void addAppointment() throws Exception {
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

    @Test
    public void likeAppointment() throws Exception {
        Address desiresAddress = new Address("Karl 1", "Mitte", "Berlin", "Germany", "10178");
        Practice desiredPractice = new Practice("BestDoc", desiresAddress);
        Doctor desiredDoctor = new Doctor("Morena De Liddo", "English", "GP", desiredPractice);
        Appointment appointment = new Appointment("Donald Duck", new Date(), desiredDoctor);

        when(appointmentService.likeAppointment(appointment.getId())).thenReturn(appointment);

        mockMvc.perform(get("/appointments/{id}/react/like", appointment.getId()))
                .andExpect(status().isOk());

        verify(appointmentService).likeAppointment(appointment.getId());
    }

    @Test
    public void dislikeAppointment() throws Exception {
        Address desiresAddress = new Address("Karl 1", "Mitte", "Berlin", "Germany", "10178");
        Practice desiredPractice = new Practice("BestDoc", desiresAddress);
        Doctor desiredDoctor = new Doctor("Morena De Liddo", "English", "GP", desiredPractice);
        Appointment appointment = new Appointment("Donald Duck", new Date(), desiredDoctor);

        when(appointmentService.dislikeAppointment(appointment.getId())).thenReturn(appointment);

        mockMvc.perform(get("/appointments/{id}/react/dislike", appointment.getId()))
                .andExpect(status().isOk());

        verify(appointmentService).dislikeAppointment(appointment.getId());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}