package morena.example.doclist.doctor;

import morena.example.doclist.BaseControllerTest;
import morena.example.doclist.controller.DoctorController;
import morena.example.doclist.controller.PracticeController;
import morena.example.doclist.domain.Address;
import morena.example.doclist.domain.Doctor;
import morena.example.doclist.domain.Practice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@ExtendWith(SpringExtension.class)
@WebMvcTest(DoctorController.class)
class DoctorControllerTest extends BaseControllerTest {

    @Test
    void getAllDoctors() throws Exception {
        List<Doctor> doctorList = new ArrayList<Doctor>();
        Practice practice = new Practice("BestDoc", new Address("Karl 1", "Mitte", "Berlin", "Germany", "10178"));
        doctorList.add(new Doctor("Morena", "English", "GP", practice));
        when(doctorService.findAll()).thenReturn(doctorList);

        mockMvc.perform(MockMvcRequestBuilders.get("/doctors")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$", hasSize(1))).andDo(print());
    }

    @Test
    void getAllEnglishSpeakingDoctors() throws Exception {
        String desiredLanguage = "English";

        List<Doctor> doctorList = new ArrayList<>();
        Practice practice = new Practice("BestDoc", new Address("Karl 1", "Mitte", "Berlin", "Germany", "10178"));
        doctorList.add(new Doctor("Morena", desiredLanguage, "GP", practice));
        when(doctorService.findByLanguage(desiredLanguage)).thenReturn(doctorList);

        mockMvc.perform(MockMvcRequestBuilders.get("/doctors")
                .param("language", desiredLanguage)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].language").value(desiredLanguage))
                .andDo(print());
    }

}