package implementation;

import com.google.inject.Inject;
import contract.Assertor;

public class JUnitAssertor implements Assertor {
    @Inject
    public JUnitAssertor(){}
    @Override
    public void equal(int expectedValue, int factValue) {
        System.out.println("JUnit Assertor equal... ...");
    }
}
