package cs.prudkytomas.backend.service;

import cs.prudkytomas.backend.domain.URLRecord;
import cs.prudkytomas.backend.dto.DtoURLRecord;
import cs.prudkytomas.backend.repository.URLRecordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.Set;

@Service
@AllArgsConstructor
public class URLRecordService {

    private final URLRecordRepository urlRecordRepository;

    public void createUrlRecord(){
        URLRecord urlRecord = new URLRecord();
        urlRecord.setUrl("A");
        urlRecord.setShortUrl("a");
        urlRecordRepository.save(urlRecord);
    }

    public void createUrlRecords(Set<URLRecord> urlRecordSet){
        urlRecordRepository.saveAll(urlRecordSet);
    }

    public boolean isDatabaseEmpty() {
        return urlRecordRepository.count() == 0 ? true : false;
    }


    public URLRecord findByShortUrl(String shortUrl) {
        return urlRecordRepository.findUrlRecordByShortUrl(shortUrl);
    }

    public URLRecord createUrlRecord(DtoURLRecord dtoURLRecord) {
        URLRecord urlRecord = new URLRecord();

        urlRecord.setUrl(dtoURLRecord.getUrl());

        String newShortUrl = generateShortUrl();
        int exist = urlRecordRepository.existNewShortUrl(newShortUrl);
        while(exist != 0){
            newShortUrl = generateShortUrl();
            exist = urlRecordRepository.existNewShortUrl(newShortUrl);
        }
        urlRecord.setShortUrl(newShortUrl);

        if(dtoURLRecord.getExpirationTime() == null){
            urlRecord.setExpirationTime(LocalDateTime.now().plusWeeks(1));
        }else{
            urlRecord.setExpirationTime(dtoURLRecord.getExpirationTime());
        }

        return urlRecordRepository.save(urlRecord);
    }

    private String generateShortUrl() {
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();

        final String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int min = 1;
        int max = 7;

        int randLen = random.nextInt(max - min + 1) + min;

        for(int i = 0; i < randLen; i++){
            randomString.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }

        return randomString.toString();
    }
}
