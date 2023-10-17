import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources",
        glue = {"api", "hooks"},
        plugin = {"pretty", "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm", "json:target/cucumber.json"},
        publish = true)
public class TestRunner {
}
