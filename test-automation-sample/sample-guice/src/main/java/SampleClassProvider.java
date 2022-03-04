import annotation.AssertProvider;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import contract.SampleProvider;
import implementation.SampleProviderImp;

import static annotation.Provider.JDBC;

public class SampleClassProvider implements Provider<SampleProvider> {
    private String dbUrl;
    @Inject
    public SampleClassProvider(@Named("JDBC URL") String dbUrl) {
    //public SampleClassProvider(@AssertProvider(JDBC) String dbUrl) {
        this.dbUrl = dbUrl;
    }
    @Override
    public SampleProvider get() {
        //String dbUrl = "jdbc:mysql://localhost:5326/sample-class-provider";
        String user = "user";
        SampleProvider sampleProvider = new SampleProviderImp(dbUrl, user);
        return sampleProvider;
    }
}
