package cs.prudkytomas.backend.config;

import cs.prudkytomas.backend.repository.URLRecordRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@AllArgsConstructor
public class TimeExpirationChecker {

    private URLRecordRepository urlRecordRepository;

    @Scheduled(cron = "0 0 * * * ?")
    public void checkExpirationTime(){
        urlRecordRepository.removeRowsWithExpiration();
    }
}
