package entities;

import io.cucumber.guice.ScenarioScoped;

@ScenarioScoped
public class TextContextImp implements TextContext {
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    private String data;
}
