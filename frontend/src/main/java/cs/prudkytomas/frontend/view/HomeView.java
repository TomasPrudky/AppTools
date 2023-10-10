package cs.prudkytomas.frontend.view;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.Lumo;

@Route("")
public class HomeView extends AppLayout {

    public HomeView() {
        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1("MyApp");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");

        Tab urlShortener = new Tab(VaadinIcon.INPUT.create(), new Span("URL Shortener"));
        Tab stopwatch = new Tab(VaadinIcon.TIMER.create(), new Span("Stopwatch"));
        Tab guesword = new Tab(VaadinIcon.TEXT_LABEL .create(),
                new Span("Gues word"));

        Tabs tabs = new Tabs(urlShortener, stopwatch, guesword);
        tabs.setOrientation(Tabs.Orientation.VERTICAL);

        urlShortener.getElement().addEventListener("click", e -> {
            setContent(new ShortenerView());
        });

        stopwatch.getElement().addEventListener("click", e -> {
            setContent(new StopwatchView());
        });

        guesword.getElement().addEventListener("click", e -> {
            setContent(new GuesWord());
        });

        Button toggleButton = new Button(VaadinIcon.MOON.create(), click -> {
            ThemeList themeList = this.getElement().getThemeList();

            if (themeList.contains(Lumo.DARK)) {
                themeList.remove(Lumo.DARK);
            } else {
                themeList.add(Lumo.DARK);
            }
        });

        VerticalLayout verticalLayout = new VerticalLayout(toggleButton);
        verticalLayout.setWidthFull();
        verticalLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.END, toggleButton);

        addToDrawer(tabs);
        addToNavbar(toggle, title, verticalLayout);
    }
}
