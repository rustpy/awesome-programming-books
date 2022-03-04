import annotation.*;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import contract.Assertor;
import contract.DatabaseAccess;
import contract.SampleProvider;
import contract.SpellChecker;
import domain.Context;
import implementation.*;

import java.lang.reflect.AnnotatedElement;

import static annotation.Provider.*;

public class TextEditorModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(SpellChecker.class).to(SpellCheckerImpl.class);
        bindInterceptor(
                Matchers.any(),
                Matchers.annotatedWith(CallTracker.class),
                new CallTrackerService()
            );
//        bind(Assertor.class).annotatedWith(UseJUnit.class).to(JUnitAssertor.class);
//        bind(Assertor.class).annotatedWith(UseTestNG.class).to(TestNGAssertor.class);
        //bind(Assertor.class).annotatedWith(Names.named("JUnit")).to(JUnitAssertor.class);

        bind(Assertor.class).annotatedWith(AssertProviders.assertProvider(JUnit)).to(JUnitAssertor.class);
        bind(Assertor.class).annotatedWith(AssertProviders.assertProvider(TestNG)).to(TestNGAssertor.class);

        Context context = new Context("test data");

        bind(Context.class).annotatedWith(Names.named("Context")).toInstance(context);

        bindConstant().annotatedWith(Names.named("JDBC URL")).to("jdbc:mysql://localhost/pizza");
        //bind(String.class).annotatedWith(AssertProviders.assertProvider(JDBC)).toInstance("jdbc:mysql://localhost/pizza111");
        bind(SampleProvider.class).toProvider(SampleClassProvider.class);

        bind(DatabaseAccess.class).to(DatabaseAccessImp.class);
        binder().requireAtInjectOnConstructors();
//        try {
//            bind(DatabaseAccess.class).toConstructor(DatabaseAccessImp.class.getConstructor(String.class));
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
    }

//    @Provides @DbAction
//    public SampleProvider provideSampleProvider(@Named("JDBC URL") String dbUrl) {
//        //String dbUrl = "jdbc:mysql://localhost:5326/emp";
//        String user = "user";
//        SampleProvider sampleProvider = new SampleProviderImp(dbUrl, user);
//        return sampleProvider;
//    }
}
