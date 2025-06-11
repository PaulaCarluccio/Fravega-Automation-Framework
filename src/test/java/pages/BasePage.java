package pages;

import java.time.Duration;
import java.util.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverManager;
import utils.ReportManager;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final int DEFAULT_TIMEOUT = 10;
    protected ReportManager reportManager;

    // -------- Constructor --------
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        this.reportManager = new ReportManager(driver);
    }

    // -------- Navegación --------
    public void navigateTo(String url) {
        driver.get(url);
        waitForPageToLoad();
    }

    // -------- Interacciones Web --------
    public WebElement find(String locator) {
        waitForPageToLoad();
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    public List<WebElement> findElements(String locator) {
        waitForPageToLoad();
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(locator)));
    }

    public void clickElement(String locator) {
        try {
            waitForPageToLoad();
            focus(locator);
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
            element.click();
        } catch (Exception e) {
            System.out.println("No se pudo hacer click en el elemento: " + locator);
        }
    }

    public void write(String locator, String keysToSend) {
        focus(locator);
        find(locator).clear();
        find(locator).sendKeys(keysToSend);
    }

    public void focus(String locator) {
        try {
            WebElement element = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);

            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            System.out.println("No se pudo hacer focus en el elemento: " + locator + " - " + e.getMessage());
        }
    }

    // -------- Esperas --------
    public void waitForPageToLoad() {
        WebDriver driver = DriverManager.getDriver();
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT)).until(webDriver ->
                ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        );
    }

    // -------- Validaciones --------
    public void validateElementIsVisible(String locator) {
        waitForPageToLoad();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }

    public void validateElementIsClickable(String locator) {
        waitForPageToLoad();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
    }

    public void validateElementIsNotVisible(String locator) {
        waitForPageToLoad();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
    }

    // -------- Scraping de textos --------
    public String getElementText(String locator) {
        return find(locator).getText();
    }

    public List<String> getElementTextValues(String locator) {
        List<WebElement> elements = findElements(locator);
        List<String> texts = new ArrayList<>();
        for (WebElement element : elements) {
            texts.add(element.getText());
        }
        return texts;
    }
}
