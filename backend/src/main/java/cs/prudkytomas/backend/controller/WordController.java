package cs.prudkytomas.backend.controller;

import cs.prudkytomas.backend.domain.URLRecord;
import cs.prudkytomas.backend.domain.Word;
import cs.prudkytomas.backend.dto.DtoURLRecord;
import cs.prudkytomas.backend.dto.DtoWord;
import cs.prudkytomas.backend.service.WordService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/word")
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class WordController {

    private final WordService wordService;

    private final ModelMapper modelMapper;

    @GetMapping
    public DtoWord getRandomWord(){
        return convertToDto(wordService.getRandomWord());
    }

    private DtoWord convertToDto(Word word) {
        DtoWord dtoWord = modelMapper.map(word, DtoWord.class);
        return dtoWord;
    }
}
