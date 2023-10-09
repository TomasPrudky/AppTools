package cs.prudkytomas.frontend.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.shared.communication.PushMode;
import cs.prudkytomas.frontend.stopwatch.Stopwatch;

@Push(PushMode.AUTOMATIC)
public class StopwatchView extends VerticalLayout {

    private VerticalLayout verticalLayout;
    private Button startButton;
    private Button stopButton;
    private H1 time;

    private long startTime = 0;
    private Stopwatch stopwatch;

    public StopwatchView() {
        stopwatch = new Stopwatch();

        verticalLayout = new VerticalLayout();
        verticalLayout.setWidthFull();
        verticalLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        time = new H1("00:00,00");

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        startButton = new Button("START");
        stopButton = new Button("STOP");

        startButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
        stopButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
        horizontalLayout.add(startButton, stopButton);


        startButton.addClickListener(e -> stopwatch.start());

        stopButton.addClickListener(e -> {
            stopwatch.stop();
            time.setText(stopwatch.getActualTime());
        });

        verticalLayout.add(new H1("Stopwatch"), time, horizontalLayout);

        add(verticalLayout);
    }


}
