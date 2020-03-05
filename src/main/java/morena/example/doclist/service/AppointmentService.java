package morena.example.doclist.service;

import morena.example.doclist.domain.Appointment;
import morena.example.doclist.domain.Doctor;
import morena.example.doclist.repository.AppointmentRepository;
import morena.example.doclist.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@ComponentScan("morena.example.docdirectory.repository")
public class AppointmentService {

    private AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment addAppointment(Appointment appointment){
       return appointmentRepository.save(appointment);
    }
}
