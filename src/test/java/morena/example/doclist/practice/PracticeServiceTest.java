package morena.example.doclist.practice;

import morena.example.doclist.domain.Address;
import morena.example.doclist.domain.Practice;
import morena.example.doclist.repository.PracticeRepository;
import morena.example.doclist.service.PracticeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PracticeServiceTest {

    @Autowired
    private PracticeRepository practiceRepository;

    @BeforeEach
    void init() {
        practiceRepository.deleteAll();
    }

    @Test
    void getAllPractices() {
        Practice practiceSample = new Practice("BestDoc", "Karl 1");
        practiceRepository.save(practiceSample);
        PracticeService practiceService = new PracticeService(practiceRepository);

        List<Practice> practiceList = practiceService.findAll();
        Practice lastPractice = practiceList.get(practiceList.size() - 1);

        assertEquals(practiceSample.getId(), lastPractice.getId());
        assertEquals(practiceSample.getName(), lastPractice.getName());
        assertEquals(practiceSample.getAddress(), lastPractice.getAddress());
    }
}