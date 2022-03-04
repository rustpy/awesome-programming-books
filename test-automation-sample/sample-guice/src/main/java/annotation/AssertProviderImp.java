package annotation;

import com.google.inject.internal.Annotations;
import com.google.inject.name.Named;

import java.lang.annotation.Annotation;


public class AssertProviderImp implements AssertProvider {
    private Provider value;
    public AssertProviderImp(Provider value) {
        this.value = value;
    }

    @Override
    public Provider value() {
        return value;
    }

    @Override
    public int hashCode() {
        // This is specified in java.lang.Annotation.
        // (name.hashCode() * 127) ^ value.hashCode()
        return (127 * "value".hashCode()) ^ value.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AssertProvider)) {
            return false;
        }
        AssertProvider other = (AssertProvider) o;
        return value.equals(other.value());
    }

    @Override
    public String toString() {
        return "@" + AssertProvider.class.getName() + "(" + Annotations.memberValueString("value", value) + ")";
    }

    /**
     * Returns the annotation type of this annotation.
     *
     * @return the annotation type of this annotation
     */
    @Override
    public Class<? extends Annotation> annotationType() {
        return AssertProvider.class;
    }
}
