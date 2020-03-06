package morena.example.doclist.doctor;

import morena.example.doclist.domain.Address;
import morena.example.doclist.domain.Doctor;
import morena.example.doclist.domain.Practice;
import morena.example.doclist.repository.AppointmentRepository;
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
    @Autowired
    private AppointmentRepository appointmentRepository;

    private Practice practiceSample = new Practice("BestDoc", new Address("Karl 1", "Mitte", "Berlin", "Germany", "10178"));


    @BeforeEach
    void init() {
        appointmentRepository.deleteAll();
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

    @Test
    void findDoctorsByPracticeCityAndArea() {
        String desiredArea = "Mitte";
        String desiredCity = "Berlin";

        Address wrongAreaAddress = new Address("Boxhagener Platz 1", "Friedhain", "Berlin", "Germany", "10245");
        Practice wrongAreaPractice = new Practice("MehDoc", wrongAreaAddress);
        practiceRepository.save(wrongAreaPractice);

        Address wrongCityAddress = new Address("Magic Street 1", desiredArea, "Topolinia", "Calisota", "55555");
        Practice wrongCityPractice = new Practice("BadDoc", wrongCityAddress);
        practiceRepository.save(wrongCityPractice);

        doctorRepository.save(new Doctor("Morena", "English", "GP", practiceSample));
        doctorRepository.save(new Doctor("Akshay", "English", "GP", practiceSample));
        doctorRepository.save(new Doctor("Bob", "Italian", "Pediatrician", wrongAreaPractice));
        doctorRepository.save(new Doctor("Laura", "German", "Pediatrician", wrongCityPractice));

        DoctorService doctorService = new DoctorService(doctorRepository);

        List<Doctor> doctorList = doctorService.findByPracticeCityAndArea(desiredCity, desiredArea);

        assertEquals(2, doctorList.size());
        System.out.println(doctorList);
        for (Doctor doc : doctorList) {
            assertEquals(desiredCity, doc.getPractice().getAddress().getCity());
            assertEquals(desiredArea, doc.getPractice().getAddress().getArea());
        }
    }
}