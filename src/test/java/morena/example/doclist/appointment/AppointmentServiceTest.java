package morena.example.doclist.appointment;

import morena.example.doclist.domain.Address;
import morena.example.doclist.domain.Appointment;
import morena.example.doclist.domain.Doctor;
import morena.example.doclist.domain.Practice;
import morena.example.doclist.repository.AppointmentRepository;
import morena.example.doclist.repository.DoctorRepository;
import morena.example.doclist.repository.PracticeRepository;
import morena.example.doclist.service.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class AppointmentServiceTest {
    String pattern = "MM-dd-yyyy:HH.mm";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PracticeRepository practiceRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @BeforeEach
    void init() {
        appointmentRepository.deleteAll();
        doctorRepository.deleteAll();
        practiceRepository.deleteAll();;
    }

    @Test
    void addAppointment() {
        Address desiresAddress = new Address("Karl 1", "Mitte", "Berlin", "Germany", "10178");
        Practice desiredPractice = new Practice("BestDoc", desiresAddress);
        practiceRepository.save(desiredPractice);
        Doctor desiredDoctor = new Doctor("Morena De Liddo", "English", "GP", desiredPractice);
        doctorRepository.save(desiredDoctor);
        Date date = new Date(System.currentTimeMillis());
        Appointment newAppointment = new Appointment("Donald Duck", date, desiredDoctor);

        AppointmentService appointmentService = new AppointmentService(appointmentRepository);
        appointmentService.addAppointment(newAppointment);

        Appointment lastAppointment = appointmentRepository.findTopByOrderByIdDesc();
        assertNotEquals(null, lastAppointment);
        assertEquals(lastAppointment.getPatientName(), newAppointment.getPatientName());
        assertEquals(lastAppointment.getDoctor(), newAppointment.getDoctor());
        assertEqualDates(lastAppointment.getDate(), newAppointment.getDate());
    }

    void assertEqualDates(Date date1, Date date2) {
        assertEquals(simpleDateFormat.format(date1), simpleDateFormat.format(date2));
    }
}