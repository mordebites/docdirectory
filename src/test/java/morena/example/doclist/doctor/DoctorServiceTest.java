package morena.example.doclist.doctor;

import morena.example.doclist.domain.Doctor;
import morena.example.doclist.domain.Practice;
import morena.example.doclist.repository.DoctorRepository;
import morena.example.doclist.repository.PracticeRepository;
import morena.example.doclist.service.DoctorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class DoctorServiceTest {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PracticeRepository practiceRepository;

    private Practice practiceSample = new Practice("BestDoc", "Karl 1");


    @BeforeEach
    void init() {
        doctorRepository.deleteAll();
        practiceRepository.deleteAll();

        practiceRepository.save(practiceSample);
    }

    @Test
    void findAllDoctors() {
        Doctor doctorSample = new Doctor("Morena", "English", "GP", practiceSample);
        doctorRepository.save(doctorSample);
        DoctorService doctorService = new DoctorService(doctorRepository);

        List<Doctor> doctorList = doctorService.findAll();
        Doctor lastDoctor = doctorList.get(doctorList.size() - 1);

        assertEquals(doctorSample.getId(), lastDoctor.getId());
        assertEquals(doctorSample.getName(), lastDoctor.getName());
        assertEquals(doctorSample.getType(), lastDoctor.getType());
        assertEquals(doctorSample.getLanguage(), lastDoctor.getLanguage());
        assertEquals(doctorSample.getPractice().getId(), lastDoctor.getPractice().getId());
    }

    @Test
    void findDoctorsByLanguage() {
        String desiredLanguage = "English";
        Practice practice = new Practice("BestDoc", "address");

        doctorRepository.save(new Doctor("Morena", desiredLanguage, "GP", practiceSample));
        doctorRepository.save(new Doctor("Akshay", desiredLanguage, "GP", practiceSample));
        doctorRepository.save(new Doctor("Bob", "Italian", "Pediatrician", practiceSample));
        DoctorService doctorService = new DoctorService(doctorRepository);

        List<Doctor> doctorList = doctorService.findByLanguage(desiredLanguage);

        assertEquals(2, doctorList.size());
        for (Doctor doc : doctorList) {
            assertEquals(desiredLanguage, doc.getLanguage());
        }
    }

    @Test
    void findDoctorsByType() {
        String desiredType = "GP";
        doctorRepository.save(new Doctor("Morena", "English", desiredType, practiceSample));
        doctorRepository.save(new Doctor("Akshay", "English", desiredType, practiceSample));
        doctorRepository.save(new Doctor("Bob", "Italian", "Pediatrician", practiceSample));
        DoctorService doctorService = new DoctorService(doctorRepository);

        List<Doctor> doctorList = doctorService.findByType(desiredType);

        assertEquals(2, doctorList.size());
        for (Doctor doc : doctorList) {
            assertEquals(desiredType, doc.getType());
        }
    }
}