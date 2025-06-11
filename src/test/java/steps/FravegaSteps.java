package steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.FravegaPage;
import utils.DriverManager;

public class FravegaSteps {
    WebDriver driver = DriverManager.getDriver();
    private final FravegaPage fravegaPage = new FravegaPage(driver);


    @Given("el usuario accede al sitio web de Frávega")
    public void navigateToHomePage() {
        fravegaPage.navigateToFravegaPage();
        fravegaPage.clickCambiarUbicacion();

    }

    @Then("se visualiza el popup con el mensaje {string}")
    public void validateMessage(String mensaje) {
        fravegaPage.validaMensajeDelPopUpUbicacion(mensaje);
    }

    @When("se ingresa el código postal {string}")
    public void enterPostalCode(String codigoPostal) {
        fravegaPage.ingresaCodigoPostal(codigoPostal);
    }

    @And("hace clic en el botón Guardar")
    public void clickOnSaveButton() {
        fravegaPage.clickOnGuardarButton();
    }

    @Then("el popup se cierra correctamente")
    public void popUpIsClosed() {
        fravegaPage.seCierraPopUp();
    }

    @And("se visualiza un mensaje confirmando el cambio de ubicación")
    public void toastExito() {
        fravegaPage.validaToastExitoso();
        fravegaPage.validaCodigoPostal();

    }
}