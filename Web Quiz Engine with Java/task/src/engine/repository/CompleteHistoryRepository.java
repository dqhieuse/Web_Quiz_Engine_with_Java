package engine.repository;

import engine.model.CompleteHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompleteHistoryRepository extends JpaRepository<CompleteHistory, Long> {
//    @Query(value = "SELECT ch.quiz.id AS id, ch.completedAt AS completedAt FROM CompleteHistory ch WHERE ch.author.email = ?1",
//    countQuery = "SELECT COUNT(ch) FROM CompleteHistory ch WHERE ch.author.email = ?1")
    Page<CompleteHistory> getCompleteHistoriesByAuthor_Email(String authorEmail, Pageable pageable);
}
