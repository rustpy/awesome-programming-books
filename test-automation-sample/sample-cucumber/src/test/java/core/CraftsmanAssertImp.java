package core;

import org.junit.Assert;

public class CraftsmanAssertImp implements CraftsmanAssert {
    public void assertEquals(int expected, int actual) {
        Assert.assertEquals(expected, actual);
    }
}
