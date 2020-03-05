package morena.example.doclist;

import morena.example.doclist.controller.DoctorController;
import morena.example.doclist.service.AppointmentService;
import morena.example.doclist.service.DoctorService;
import morena.example.doclist.service.PracticeService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


@ExtendWith(SpringExtension.class)
public
class BaseControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected DoctorService doctorService;

    @MockBean
    protected PracticeService practiceService;

    @MockBean
    protected AppointmentService appointmentService;
}