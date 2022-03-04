package annotation;

import com.google.auto.value.AutoAnnotation;

public class AssertProviders {
    @AutoAnnotation
    public static AssertProvider assertProvider(Provider value) {
        //return AutoAnnotation_AssertProviders_assertProvider(value);
        return new AssertProviderImp(value);
    }
}
