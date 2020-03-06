package morena.example.doclist.controller;

import morena.example.doclist.domain.Appointment;
import morena.example.doclist.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@ComponentScan("morena.example.doclist.service")
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> addAppointment(@RequestBody Appointment appointment, UriComponentsBuilder ucBuilder) {
        Appointment newAppointment = appointmentService.addAppointment(appointment);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/appointments/{id}").buildAndExpand(newAppointment.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}/react/like")
    public ResponseEntity<Appointment> likeAppointment(@PathVariable("id") String id, UriComponentsBuilder ucBuilder) {
        long longId = Long.parseLong(id);
        appointmentService.likeAppointment(longId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //TODO refactor
    @GetMapping(value = "/{id}/react/dislike")
    public ResponseEntity<Appointment> dislikeAppointment(@PathVariable("id") String id, UriComponentsBuilder ucBuilder) {
        long longId = Long.parseLong(id);
        appointmentService.dislikeAppointment(longId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}