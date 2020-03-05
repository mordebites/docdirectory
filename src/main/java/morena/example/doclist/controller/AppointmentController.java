package morena.example.doclist.controller;

import morena.example.doclist.domain.Appointment;
import morena.example.doclist.domain.Doctor;
import morena.example.doclist.service.AppointmentService;
import morena.example.doclist.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@ComponentScan("morena.example.doclist.service")
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> addAppointment(@RequestBody Appointment appointment, UriComponentsBuilder ucBuilder){
        Appointment newAppointment = appointmentService.addAppointment(appointment);
        HttpHeaders headers = new HttpHeaders();
        System.out.println(newAppointment);
        headers.setLocation(ucBuilder.path("/appointments/{id}").buildAndExpand(newAppointment.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

}