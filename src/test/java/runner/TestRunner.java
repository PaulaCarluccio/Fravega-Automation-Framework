package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeSuite;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "steps",
        plugin = {
                "pretty",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" // Reporte ExtentReports
        },
        tags = "@Fravega"
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @BeforeSuite
    public void setupEnvironment() {
        System.setProperty("cucumber.publish.quiet", "true");
        System.setProperty("browser", "chrome"); // "chrome", "firefox", o "edge"
        System.setProperty("headless", "true"); // "true" para modo sin interfaz gráfica
    }
}