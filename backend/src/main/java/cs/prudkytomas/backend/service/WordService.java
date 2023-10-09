package cs.prudkytomas.backend.service;

import cs.prudkytomas.backend.domain.Word;
import cs.prudkytomas.backend.repository.WordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class WordService {

    private WordRepository wordRepository;

    public void createWord(Word word){
        wordRepository.save(word);
    }

    public void createWords(Set<String> wordList){
        Set<Word> wordSet = new HashSet<>();
        for (String s : wordList) {
            Word word = new Word();
            word.setWordString(s);
            wordSet.add(word);
        }
        wordRepository.saveAll(wordSet);
    }

    public Word getRandomWord(){
        return wordRepository.getRandomWord();
    }

    public boolean isDatabaseEmpty() {
        return wordRepository.count() == 0;
    }
}
