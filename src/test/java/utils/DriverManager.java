package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DriverManager {

    private static WebDriver driver;

    public static void initializeDriver(String browser) {
        boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        switch (browser.toLowerCase()) {
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (isHeadless) firefoxOptions.addArguments("--headless");

                // Bloquea la ubicación en Firefox, esto es para que no salga la pregunta de si queremos permitir al browser acceder a nuestra ubicación.
                firefoxOptions.addPreference("permissions.default.geo", 2); // 2 = bloquear
                firefoxOptions.addPreference("geo.prompt.testing", true);
                firefoxOptions.addPreference("geo.prompt.testing.allow", false);

                driver = new FirefoxDriver(firefoxOptions);
            }

            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                if (isHeadless) edgeOptions.addArguments("--headless");

                // Bloquea la ubicación en Edge
                Map<String, Object> edgePrefs = new HashMap<>();
                edgePrefs.put("profile.default_content_setting_values.geolocation", 2); // 2 = bloquear
                edgeOptions.setExperimentalOption("prefs", edgePrefs);

                driver = new EdgeDriver(edgeOptions);
            }

            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (isHeadless) {
                    chromeOptions.addArguments("--headless", "--no-sandbox", "--disable-gpu");
                }

                // Bloquea la ubicación en Chrome
                Map<String, Object> chromePrefs = new HashMap<>();
                chromePrefs.put("profile.default_content_setting_values.geolocation", 2); // 2 = bloquear
                chromeOptions.setExperimentalOption("prefs", chromePrefs);

                driver = new ChromeDriver(chromeOptions);
            }
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}