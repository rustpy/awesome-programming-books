package domain;

import annotation.AssertProvider;
import annotation.CallTracker;
import annotation.Provider;
import annotation.UseJUnit;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import contract.Assertor;
import contract.DatabaseAccess;
import contract.SampleProvider;
import contract.SpellChecker;
import implementation.SpellCheckerImpl;

import java.util.logging.Level;
import java.util.logging.Logger;

import static annotation.Provider.TestNG;


public class TextEditor {
    //@Inject
    private SpellChecker spellChecker;
    private Assertor assertorJUnit;
    private Assertor assertorTestNG;

    @Inject
    public TextEditor(
            SpellChecker spellChecker,
            //@Named("TestNG") Assertor assertor,
            //@UseTestNG Assertor assertor,
            //@DbAction SampleProvider sampleProvider,
            Logger logger,
            DatabaseAccess databaseAccess,
            SampleProvider sampleProvider,
//            @Named("Context") Context context1,
//            @Named("Context") Context context2,
            @AssertProvider(TestNG) Assertor assertor) {
        this.spellChecker = spellChecker;
//        sampleProvider.someAction();
//        databaseAccess.someAction();
//        logger.warning("[waring]: this is warning message!");
//        logger.info("[info]: this is info message!");
//        logger.log(Level.FINE,"[info]: this is info message!");
    }

    @CallTracker
    public void makeSpellCheck() {
        spellChecker.checkSpelling();
//        assertorJUnit.equal(1,1);
//        assertorTestNG.equal(1,1);
    }
    //@Inject
    public  void setContext(SpellChecker spellChecker) {
        this.spellChecker = spellChecker;
    }
}