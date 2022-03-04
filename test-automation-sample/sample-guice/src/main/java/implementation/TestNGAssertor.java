package implementation;

import com.google.inject.Inject;
import contract.Assertor;

public class TestNGAssertor implements Assertor {
    @Inject
    public TestNGAssertor(){}
    @Override
    public void equal(int expectedValue, int factValue) {
        System.out.println("TestNG Assertor equal... ...");
    }
}
