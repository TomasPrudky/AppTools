package cs.prudkytomas.frontend.view;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import cs.prudkytomas.frontend.domain.URLRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Route("shortener")
@RouteAlias("shortener")
public class ShortenerView extends VerticalLayout {

    private VerticalLayout verticalLayout;
    private TextField textField;
    private RadioButtonGroup<String> radioButtonGroup;
    private Button button;
    private H2 result;

    public ShortenerView() {
        verticalLayout = new VerticalLayout();
        verticalLayout.setWidthFull();
        verticalLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        textField = new TextField();
        textField.setLabel("URL");
        textField.setWidth("80%");

        radioButtonGroup = new RadioButtonGroup<>();
        radioButtonGroup.setLabel("Expiration time");
        radioButtonGroup.setItems("1 Hour", "1 Day", "1 Month", "1 Year");
        radioButtonGroup.setValue("1 Hour");

        button = new Button("Create shorter URL");

        result = new H2("");

        verticalLayout.add(textField, radioButtonGroup, button, result);

        button.getElement().addEventListener("click", e -> {
            if(!textField.isEmpty()) createShortUrl(); else showNotification();
        });

        add(verticalLayout);
    }

    private void showNotification() {
        Notification notification = new Notification();
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);

        Div text = new Div(new Text("Failed to generate short URL! You must enter your URL!"));
        notification.add(text);
        notification.setDuration(5000);
        notification.open();
    }

    private void createShortUrl() {
        RestTemplate restTemplate = new RestTemplate();

        URLRecord urlRecord = new URLRecord();
        urlRecord.setUrl(textField.getValue());
        urlRecord.setExpirationTime(LocalDateTime.now().plusHours(1));
        urlRecord.setExpirationTime(getExpirationTimeFromCheckboxGroup());

        ResponseEntity<URLRecord> response = restTemplate.postForEntity("http://localhost:8080/api/v1/url", urlRecord, URLRecord.class);
        result.setText("http://localhost:8081/url/" + response.getBody().getShortUrl());
    }

    private LocalDateTime getExpirationTimeFromCheckboxGroup() {
        switch (radioButtonGroup.getValue()){
            case "1 Hour":
                return LocalDateTime.now().plusHours(1);
            case "1 Day":
                return LocalDateTime.now().plusDays(1);
            case "1 Month":
                return LocalDateTime.now().plusMonths(1);
            case "1 Year":
                return LocalDateTime.now().plusYears(1);
            default:
                return LocalDateTime.now().plusWeeks(1);
        }
    }
}
