package cs.prudkytomas.backend.controller;

import cs.prudkytomas.backend.dto.DtoURLRecord;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HelloController {

    @GetMapping()
    public DtoURLRecord hello(){
        return new DtoURLRecord("A", "a", LocalDateTime.now());
    }
}
