package behavior;

import com.google.inject.Inject;
import entities.TextContext;
import io.cucumber.guice.ScenarioScoped;
import io.cucumber.java.en.Given;

//@ScenarioScoped
public class StateShareSteps {
    private TextContext context;

    @Inject
    public StateShareSteps(TextContext context) {
        this.context = context;
    }

    @Given("basic state step one")
    public void basic_state_step_one() {
        this.context.setData("this is state");
        System.out.println(String.format("State is :%s", this.context.getData()));
    }


}
