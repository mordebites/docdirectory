package morena.example.doclist.controller;

import morena.example.doclist.service.PracticeService;
import morena.example.doclist.domain.Practice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ComponentScan("morena.example.doclist.service")
public class PracticeController {

    @Autowired
    private PracticeService practiceService;

    @GetMapping("/practices")
    ResponseEntity<List<Practice>> getAllPractices() {
        return new ResponseEntity<>(practiceService.findAll(), HttpStatus.OK);
    }
}