package cs.prudkytomas.frontend.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import cs.prudkytomas.frontend.domain.URLRecord;
import cs.prudkytomas.frontend.domain.Word;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class GuesWord extends VerticalLayout {

    private String czechAlphabet = "ěščřžýáíéďňóqwertzuiopúasdfghjklůyxcvbnmť".toUpperCase();
    private List<Button> buttonList;
    private VerticalLayout verticalLayout;
    private String randomWord;
    private H2 guesingWord;

    public GuesWord() {
        verticalLayout = new VerticalLayout();

        verticalLayout.setWidthFull();
        verticalLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        add(new H1("Gues Word"));

        randomWord = getRandomWord();
        guesingWord = new H2(randomWord.toUpperCase());
        verticalLayout.add(guesingWord);

        buttonList = new ArrayList<>();
        createKeyboard();
    }

    private String getRandomWord() {
        RestTemplate restTemplate = new RestTemplate();
        String request = "http://localhost:8080/api/v1/word";
        Word word = restTemplate.getForEntity(request, Word.class).getBody();
        return word.getWordString();

    }

    private void createKeyboard() {
        String tmpAlphabet = czechAlphabet;

        int countElementsInFirstRow = 12;
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < countElementsInFirstRow; j++) {
                if (tmpAlphabet.isEmpty()) break;

                Button button = new Button(String.valueOf(tmpAlphabet.charAt(0)));
                button.setMaxWidth("3%");
                button.addClickListener(event -> {
                    button.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
                });

                horizontalLayout.add(button);
                tmpAlphabet = tmpAlphabet.substring(1);
            }
            countElementsInFirstRow -= 1;
            verticalLayout.add(horizontalLayout);
            horizontalLayout = new HorizontalLayout();
        }
        add(verticalLayout);
    }
}
