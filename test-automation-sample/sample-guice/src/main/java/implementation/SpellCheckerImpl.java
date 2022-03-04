package implementation;

import com.google.inject.Inject;
import contract.SpellChecker;

public class SpellCheckerImpl implements SpellChecker {
    @Inject
    public  SpellCheckerImpl(){}
    @Override
    public void checkSpelling() {
        System.out.println("Inside checkSpelling." );
    }
}
