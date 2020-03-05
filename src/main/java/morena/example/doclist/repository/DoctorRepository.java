package morena.example.doclist.repository;

import morena.example.doclist.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<List<Doctor>> findByLanguage(String language);

    Optional<List<Doctor>> findByType(String type);

    Optional<List<Doctor>> findByPracticeAddressCityAndPracticeAddressArea(String practiceAddressCity, String practiceAddressArea);
}