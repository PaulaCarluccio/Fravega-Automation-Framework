package pages;

import java.time.Duration;
import java.util.*;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import utils.ReportManager;

public class BasePage {
    protected WebDriver driver;
    private static final int DEFAULT_TIMEOUT = 10;
    private static final int DEFAULT_POLLING = 200;
    protected ReportManager reportManager;

    // -------- Constructor --------
    public BasePage(WebDriver driver) {
        this.driver = driver;
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
        return fluentWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    public List<WebElement> findElements(String locator) {
        waitForPageToLoad();
        return fluentWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(locator)));
    }

    public void clickElement(String locator) {
        try {
            waitForPageToLoad();
            focus(locator);
            WebElement element = fluentWait().until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
            element.click();
        } catch (Exception e) {
            System.out.println("No se pudo hacer click en el elemento: " + locator);
        }
    }

    public void write(String locator, String keysToSend) {
        focus(locator);
        WebElement element = find(locator);
        element.clear();
        element.sendKeys(keysToSend);
    }

    public void focus(String locator) {
        try {
            WebElement element = fluentWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            fluentWait().until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            System.out.println("No se pudo hacer focus en el elemento: " + locator + " - " + e.getMessage());
        }
    }

    // -------- Esperas --------
    public void waitForPageToLoad() {
        fluentWait().until(driver ->
                ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete")
        );
    }

    public FluentWait<WebDriver> fluentWait() {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(DEFAULT_TIMEOUT))
                .pollingEvery(Duration.ofMillis(DEFAULT_POLLING))
                .ignoring(NoSuchElementException.class);
    }

    // -------- Validaciones --------
    public void validateElementIsVisible(String locator) {
        waitForPageToLoad();
        fluentWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }

    public void validateElementIsClickable(String locator) {
        waitForPageToLoad();
        fluentWait().until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
    }

    public void validateElementIsNotVisible(String locator) {
        fluentWait().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
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