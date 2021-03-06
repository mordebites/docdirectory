package morena.example.doclist.controller;

import morena.example.doclist.domain.Doctor;
import morena.example.doclist.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

@RestController
@ComponentScan("morena.example.doclist.service")
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public ResponseEntity<List<Doctor>> findAllDoctors() {
        return new ResponseEntity<>(doctorService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"language"})
    public ResponseEntity<List<Doctor>> findDoctorsByLanguage(@RequestParam(name = "language") String language) {
        if (language == null) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(doctorService.findByLanguage(language), HttpStatus.FOUND);
        }
    }
}