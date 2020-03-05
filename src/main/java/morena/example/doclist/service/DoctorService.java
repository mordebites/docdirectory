package morena.example.doclist.service;

import morena.example.doclist.domain.Doctor;
import morena.example.doclist.repository.DoctorRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@ComponentScan("morena.example.docdirectory.repository")
public class DoctorService {

    private DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> findAll() {
        return this.doctorRepository.findAll();
    }

    public List<Doctor> findByLanguage(String language) {
        Optional<List<Doctor>> optionalList = doctorRepository.findByLanguage(language);
        if (optionalList.isPresent()) {
            return optionalList.get();
        } else {
            return new ArrayList<>();
        }
    }

    public List<Doctor> findByType(String type) {
        Optional<List<Doctor>> optionalList = doctorRepository.findByType(type);
        if (optionalList.isPresent()) {
            return optionalList.get();
        } else {
            return new ArrayList<>();
        }
    }
}
