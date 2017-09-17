import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/features/LeadStatus.feature"},
        format = {"html:target/cucumber", "json:target/cucumber.json"},
        tags = {"@LeadStatus"})

public class RunTests {
}