import com.google.inject.Guice;
import com.google.inject.Injector;
import contract.SpellChecker;
import domain.TextEditor;
import implementation.SpellCheckerImpl;

public class GuiceSample {
    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new TextEditorModule());
        TextEditor textEditor = injector.getInstance(TextEditor.class);
        SpellChecker spellChecker = new SpellCheckerImpl();
        injector.injectMembers(spellChecker);
        textEditor.makeSpellCheck();
    }
}
