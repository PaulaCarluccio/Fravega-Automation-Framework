package steps;

import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.DriverManager;
import utils.ReportManager;

public class Hooks {

    @Before
    public void setUp(Scenario escenario) {
        String browser = System.getProperty("browser", "edge");
        DriverManager.initializeDriver(browser);
        ReportManager.setScenario(escenario);
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Captura de pantalla del error");
        }
        DriverManager.quitDriver();
    }
}