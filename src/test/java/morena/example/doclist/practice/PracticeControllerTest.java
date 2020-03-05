package morena.example.doclist.practice;

import morena.example.doclist.BaseControllerTest;
import morena.example.doclist.controller.PracticeController;
import morena.example.doclist.domain.Address;
import morena.example.doclist.domain.Practice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PracticeController.class)
class PracticeControllerTest extends BaseControllerTest {

    @Test
    void getAllPractices() throws Exception {
        List<Practice> practiceList = new ArrayList<Practice>();
        Address address = new Address("Karl 1", "Mitte", "Berlin", "Germany", "10245");
        practiceList.add(new Practice("BestDocs", "address"));
        when(practiceService.findAll()).thenReturn(practiceList);

        mockMvc.perform(MockMvcRequestBuilders.get("/practices")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$", hasSize(1))).andDo(print());
    }

}