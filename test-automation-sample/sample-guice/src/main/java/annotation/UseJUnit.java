package annotation;

import com.google.inject.BindingAnnotation;

import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Qualifier
//@BindingAnnotation
@Target({FIELD,PARAMETER,METHOD})
@Retention(RUNTIME)
public @interface UseJUnit {}
