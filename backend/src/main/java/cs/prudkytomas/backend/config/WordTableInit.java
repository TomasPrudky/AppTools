package cs.prudkytomas.backend.config;

import cs.prudkytomas.backend.service.WordService;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

@Component
@AllArgsConstructor
public class WordTableInit implements ApplicationRunner {

    private final WordService wordService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(wordService.isDatabaseEmpty()){
            Set<String> wordList = loadWordsFromFile();
            wordService.createWords(wordList);
        }
    }

    private Set<String> loadWordsFromFile() throws IOException {
        Set<String> uniqueLines = new HashSet<>();
        File file = new ClassPathResource("wordsCz.txt").getFile();

        Scanner myReader = new Scanner(file, "UTF-8");
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            uniqueLines.add(data);
        }
        myReader.close();

        return uniqueLines;
    }
}
