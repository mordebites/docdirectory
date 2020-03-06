package morena.example.doclist.appointment;

import morena.example.doclist.domain.*;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AppointmentServiceTest {
    private final String pattern = "MM-dd-yyyy:HH.mm";
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    private Appointment newAppointment;
    private AppointmentService appointmentService;

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
        practiceRepository.deleteAll();

        Address desiresAddress = new Address("Karl 1", "Mitte", "Berlin", "Germany", "10178");
        Practice desiredPractice = new Practice("BestDoc", desiresAddress);
        practiceRepository.save(desiredPractice);
        Doctor desiredDoctor = new Doctor("Morena De Liddo", "English", "GP", desiredPractice);
        doctorRepository.save(desiredDoctor);
        Date date = new Date(System.currentTimeMillis());
        newAppointment = new Appointment("Donald Duck", date, desiredDoctor);

        appointmentService = new AppointmentService(appointmentRepository);
    }

    @Test
    void addAppointment() {
        appointmentService.addAppointment(newAppointment);

        Appointment lastAppointment = getLastAppointmentInRepo();
        assertEquals(lastAppointment.getPatientName(), newAppointment.getPatientName());
        assertEquals(lastAppointment.getDoctor(), newAppointment.getDoctor());
        assertEquals(lastAppointment.getReaction(), newAppointment.getReaction());
        assertEqualDates(lastAppointment.getDate(), newAppointment.getDate());
    }


    @Test
    void likeAppointment(){
        Appointment savedAppointment = appointmentService.addAppointment(newAppointment);
        assertEquals(Reaction.NONE, savedAppointment.getReaction());

        appointmentService.likeAppointment(savedAppointment.getId());

        Appointment lastAppointment = getLastAppointmentInRepo();
        assertEquals(Reaction.LIKE, lastAppointment.getReaction());
    }

    @Test
    void dislikeAppointment(){
        Appointment savedAppointment = appointmentService.addAppointment(newAppointment);
        assertEquals(Reaction.NONE, savedAppointment.getReaction());

        appointmentService.dislikeAppointment(savedAppointment.getId());

        Appointment lastAppointment = getLastAppointmentInRepo();
        assertEquals(Reaction.DISLIKE, lastAppointment.getReaction());
    }

    private void assertEqualDates(Date date1, Date date2){
        assertEquals(simpleDateFormat.format(date1), simpleDateFormat.format(date2));
    }

    private Appointment getLastAppointmentInRepo(){
        Appointment lastAppointment = appointmentRepository.findTopByOrderByIdDesc();
        assertNotNull(lastAppointment);
        return lastAppointment;
    }
}
