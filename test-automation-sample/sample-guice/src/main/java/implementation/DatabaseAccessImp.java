package implementation;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import contract.DatabaseAccess;

public class DatabaseAccessImp implements DatabaseAccess {

    private String dbUrl = "[empty]";

    @Inject
    public DatabaseAccessImp() {
    }

    //public DatabaseAccessImp(@Named("JDBC URL") String dbUrl) {
    public DatabaseAccessImp(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    @Override
    public void someAction() {
        System.out.println(this.dbUrl);
    }
}