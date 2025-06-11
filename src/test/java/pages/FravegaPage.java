package pages;

import org.openqa.selenium.WebDriver;
import static locators.FravegaHome.*;

import org.testng.Assert;
import utils.ConfigLoader;
import java.util.List;

public class FravegaPage extends BasePage {
    private String BASE_URL;
    private String codigoPostal;

    public FravegaPage(WebDriver driver) {
        super(driver);
        loadCredentials();
    }

    private void loadCredentials() {
        ConfigLoader configLoader = new ConfigLoader("test-data");
        BASE_URL = configLoader.getProperty("base.url");
    }

    public void navigateToFravegaPage() {
        waitForPageToLoad();
        navigateTo(BASE_URL);
        validateElementIsVisible(fravegaLogo);
    }

    public void clickCambiarUbicacion() {
        clickElement(cambiarUbicacionButton);
        validateElementIsVisible(modalCambiarUbicacion);
        reportManager.attachScreenshot("Modal Cambiar Ubicación");
    }

    public void validaMensajeDelPopUpUbicacion(String mensaje) {
        String mensajePopUp = getElementText(modalTitle);
        Assert.assertEquals(mensajePopUp, mensaje);
    }

    public void ingresaCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
        validateElementIsClickable(inputCodigoPostal);
        write(inputCodigoPostal, codigoPostal);
    }

    public void clickOnGuardarButton() {
        reportManager.attachScreenshot("Codigo Postal Ingresado");
        clickElement(guardarButton);
    }

    public void seCierraPopUp() {
        validateElementIsNotVisible(modalCambiarUbicacion);
    }

    public void validaToastExitoso() {
        List<String> mensajeEnToast = getElementTextValues(mensajeToast);
        String actualText = String.join(" ", mensajeEnToast).trim();
        String expectedText = "Te estamos mostrando las mejores opciones de entrega para tu ubicación.";
        Assert.assertEquals(actualText, expectedText);
    }

    public void validaCodigoPostal() {
        validateElementIsVisible(estasEnCodigoPostal(codigoPostal));
        reportManager.attachScreenshot("Nueva Ubicación");
    }
}