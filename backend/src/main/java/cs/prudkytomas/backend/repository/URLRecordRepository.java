package cs.prudkytomas.backend.repository;

import cs.prudkytomas.backend.domain.URLRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface URLRecordRepository extends JpaRepository<URLRecord, Long> {

    @Query("SELECT u FROM URLRecord u WHERE u.shortUrl = :shortUrl")
    URLRecord findUrlRecordByShortUrl(@Param("shortUrl") String shortUrl);

    @Query("SELECT u FROM URLRecord u WHERE u.url IS NULL ORDER BY u.id LIMIT 1")
    URLRecord getFirstEmpty();

    @Query("SELECT COUNT(u) FROM URLRecord u WHERE u.shortUrl = :shortUrl")
    Integer existNewShortUrl(@Param("shortUrl") String newShortUrl);

    @Modifying
    @Transactional
    @Query("DELETE FROM URLRecord u WHERE u.expirationTime < CURRENT_TIMESTAMP")
    void removeRowsWithExpiration();

}
