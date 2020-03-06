package morena.example.doclist.service;

import morena.example.doclist.domain.Appointment;
import morena.example.doclist.domain.Reaction;
import morena.example.doclist.repository.AppointmentRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@ComponentScan("morena.example.docdirectory.repository")
public class AppointmentService {

    private AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment addAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Appointment likeAppointment(long id) throws RuntimeException { //TODO improve exception
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if (optionalAppointment.isEmpty()) throw new RuntimeException();
        Appointment appToLike = optionalAppointment.get();
        appToLike.setReaction(Reaction.LIKE);
        return appointmentRepository.save(appToLike);
    }

    //TODO extract react method
    public Appointment dislikeAppointment(long id) throws RuntimeException { //TODO improve exception
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if (optionalAppointment.isEmpty()) throw new RuntimeException();
        Appointment appToLike = optionalAppointment.get();
        appToLike.setReaction(Reaction.DISLIKE);
        return appointmentRepository.save(appToLike);
    }
}
