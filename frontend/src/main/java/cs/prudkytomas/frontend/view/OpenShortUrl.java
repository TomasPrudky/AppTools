package cs.prudkytomas.frontend.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import cs.prudkytomas.frontend.domain.URLRecord;
import org.springframework.web.client.RestTemplate;

@Route("url")
public class OpenShortUrl extends Div implements HasUrlParameter<String> {

    private String key;

    public OpenShortUrl(){
        add(new H1("Redirecting..."));
    }

    private String getFullUrl(String key) {
        RestTemplate restTemplate = new RestTemplate();
        String request = "http://localhost:8080/api/v1/url?key=" + key;
        URLRecord urlRecord = restTemplate.getForEntity(request, URLRecord.class).getBody();
        return urlRecord.getUrl();
    }

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        String fullurl = getFullUrl(parameter);
        System.out.println(parameter);
        System.out.println(fullurl);

        UI.getCurrent().getPage().executeJs("window.location.href = '" + fullurl + "'");
    }
}
