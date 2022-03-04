package domain;

public class Context {
    private final String data;

    public Context(String data) {
        this.data = data;
    }
    public void showData() {
        System.out.println(this.data);
    }
}
