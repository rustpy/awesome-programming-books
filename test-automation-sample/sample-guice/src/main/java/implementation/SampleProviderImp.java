package implementation;

import com.google.inject.Inject;
import contract.SampleProvider;

public class SampleProviderImp implements SampleProvider {
    private String dbUrl;
    private String user;
    @Inject
    public SampleProviderImp(){}
    public SampleProviderImp(String dbUrl,String user){
        this.dbUrl = dbUrl;
        this.user = user;
    }
    @Override
    public void someAction() {
        System.out.println("SampleProviderImp - someAction... ... | " + dbUrl);
    }
}
