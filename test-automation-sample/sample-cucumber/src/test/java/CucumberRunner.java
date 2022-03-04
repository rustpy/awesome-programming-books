import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty",
//                "html:target/cucumber-report.html",
//                "json:target/cucumber-json.json",
                "html:test-output",
                "json:target/cucumber-report/cucumber.json",
                "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"
        },
        //tags = "@debug",
        features = "src/test/resources/test-case",
        objectFactory = CustomObjectFactory.class
)
public class CucumberRunner {
}
