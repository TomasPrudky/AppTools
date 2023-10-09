package cs.prudkytomas.backend.repository;

import cs.prudkytomas.backend.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    @Query("SELECT w FROM Word w ORDER BY RANDOM() LIMIT 1")
    Word getRandomWord();
}
