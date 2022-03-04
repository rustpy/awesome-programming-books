import com.google.inject.AbstractModule;
import core.CraftsmanAssert;
import core.CraftsmanAssertImp;
import entities.TextContext;
import entities.TextContextImp;

public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(TextContext.class).to(TextContextImp.class);
        bind(CraftsmanAssert.class).to(CraftsmanAssertImp.class);
        // ... (further bindings)
    }
}
