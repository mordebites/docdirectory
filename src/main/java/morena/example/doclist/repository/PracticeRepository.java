package morena.example.doclist.repository;

import morena.example.doclist.domain.Practice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PracticeRepository extends JpaRepository<Practice, Long> {
}