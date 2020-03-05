package morena.example.doclist.service;

import morena.example.doclist.domain.Practice;
import morena.example.doclist.repository.PracticeRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PracticeService {

    private PracticeRepository practiceRepository;

    public PracticeService(PracticeRepository practiceRepository) {
        this.practiceRepository = practiceRepository;
    }

    public List<Practice> findAll() {
        return this.practiceRepository.findAll();
    }

}
