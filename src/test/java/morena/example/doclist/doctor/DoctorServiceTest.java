package morena.example.doclist.doctor;

import morena.example.doclist.domain.Doctor;
import morena.example.doclist.domain.Practice;
import morena.example.doclist.domain.Address;
import morena.example.doclist.repository.DoctorRepository;
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

    @BeforeEach
    void init() {
        doctorRepository.deleteAll();
    }

    @Test
    void findAllDoctors() {
        Address address = new Address("Karl 1", "Mitte", "Berlin", "Germany", "10245");
        Practice practiceSample = new Practice("BestDoc", "address");
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
        Address address = new Address("Karl 1", "Mitte", "Berlin", "Germany", "10245");
        Practice practice = new Practice("BestDoc", "address");

        doctorRepository.save(new Doctor("Morena", desiredLanguage, "GP", practice));
        doctorRepository.save(new Doctor("Akshay", desiredLanguage, "GP", practice));
        doctorRepository.save(new Doctor("Bob", "Italian", "Pediatrician", practice));
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
        Address address = new Address("Karl 1", "Mitte", "Berlin", "Germany", "10245");
        Practice practice = new Practice("BestDoc", "address");

        doctorRepository.save(new Doctor("Morena", "English", desiredType, practice));
        doctorRepository.save(new Doctor("Akshay", "English", desiredType, practice));
        doctorRepository.save(new Doctor("Bob", "Italian", "Pediatrician", practice));
        DoctorService doctorService = new DoctorService(doctorRepository);

        List<Doctor> doctorList = doctorService.findByType(desiredType);

        assertEquals(2, doctorList.size());
        for (Doctor doc : doctorList) {
            assertEquals(desiredType, doc.getType());
        }
    }

    @Test
    void findDoctorsByArea() {
        String desiredArea = "Mitte";
        Address firstAddress = new Address("Karl 1", desiredArea, "Berlin", "Germany", "10245");
        Practice firstPractice = new Practice("BestDoc", "firstAddress");
        Address secondAddress = new Address("Karl 5", desiredArea, "Berlin", "Germany", "10245");
        Practice secondPractice = new Practice("SecondBestDoc", "secondAddress");
        Address thirdAddress = new Address("Boxhagener Platz 1", "Friedrichshain", "Berlin", "Germany", "10178");
        Practice thirdPractice = new Practice("DrHouse", "thirdAddress");

        doctorRepository.save(new Doctor("Morena", desiredArea, "GP", firstPractice));
        doctorRepository.save(new Doctor("Akshay", desiredArea, "GP", secondPractice));
        doctorRepository.save(new Doctor("Bob", "Italian", "Pediatrician", thirdPractice));
        DoctorService doctorService = new DoctorService(doctorRepository);

        /*List<Doctor> doctorList = doctorService.findByArea(desiredArea);

        assertEquals(2, doctorList.size());
        for (Doctor doc : doctorList) {
            assertEquals(desiredArea, doc.getPractice().getArea());
        }*/
    }
}