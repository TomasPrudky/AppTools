package cs.prudkytomas.backend.controller;

import cs.prudkytomas.backend.domain.URLRecord;
import cs.prudkytomas.backend.dto.DtoURLRecord;
import cs.prudkytomas.backend.repository.URLRecordRepository;
import cs.prudkytomas.backend.service.URLRecordService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/url")
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class URLRecordController {

    private final URLRecordService urlRecordService;
    private final ModelMapper modelMapper;

    @GetMapping("/default")
    public DtoURLRecord getUrlRecord(){
        URLRecord url = new URLRecord();
        url.setUrl("D");

        return convertToDto(url);
    }

    @GetMapping()
    public DtoURLRecord getFullUrlByShortUrl(@RequestParam String key){
        return convertToDto(urlRecordService.findByShortUrl(key));
    }

    @PostMapping
    public DtoURLRecord createUrlRecord(@RequestBody DtoURLRecord dtoURLRecord){
        return convertToDto(urlRecordService.createUrlRecord(dtoURLRecord));
    }

    private DtoURLRecord convertToDto(URLRecord record) {
        DtoURLRecord dtoURLRecord = modelMapper.map(record, DtoURLRecord.class);
        return dtoURLRecord;
    }
}
