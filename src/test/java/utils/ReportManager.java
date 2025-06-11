package utils;

import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ReportManager {

    private static Scenario scenario;

    public ReportManager(WebDriver driver) {
        super();
    }

    public static void setScenario(Scenario escenario) {
        scenario = escenario;
    }

    public static Scenario getScenario() {
        return scenario;
    }

    public static byte[] captureScreenshot() {
        try {
            WebDriver driver = DriverManager.getDriver();
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void attachScreenshot(String nombreScreenshot) {
        try {
            Scenario scenario = getScenario();
            if (scenario != null) {
                scenario.attach(captureScreenshot(), "image/png", nombreScreenshot);
            } else {
                System.out.println("Escenario no disponible para adjuntar screenshot.");
            }
        } catch (Exception e) {
            System.out.println("Error al adjuntar screenshot: " + e.getMessage());
        }
    }
}
