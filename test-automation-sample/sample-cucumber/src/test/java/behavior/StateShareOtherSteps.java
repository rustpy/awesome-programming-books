package behavior;

import com.google.inject.Inject;
import entities.TextContext;
import io.cucumber.java.en.Given;

public class StateShareOtherSteps {
    private TextContext context;

    @Inject
    public StateShareOtherSteps(TextContext context) {
        this.context = context;
    }

    @Given("basic state step two")
    public void basic_state_step_two() {
        System.out.println(String.format("State is :%s", this.context.getData()));
    }

}
