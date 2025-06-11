package locators;

public class FravegaHome {
    public static String fravegaLogo = "//a[@data-test-id='fravega-logo']";
    public static String cambiarUbicacionButton = "//button[text()='Cambiar ubicación']";
    public static String modalCambiarUbicacion = "//div[@data-test-id='geo-modal-wrapper']";

    //Lo hice por posición porque queremos validar el texto luego
    public static String modalTitle = "(//div[@data-test-id='geo-modal-wrapper']//div)[1]/div";
    public static String inputCodigoPostal = "//input[@data-test-id='header-geo-location-form-postal-number']";
    public static String guardarButton = "//button[@data-test-id='button-save-postal-code']";

    public static String mensajeToast = "//button[normalize-space(text())='Cambiar ubicación']/ancestor::div[2]//div//p";

    //Creo el locator dinamico para que pueda encontrarlo por numero de cp
    public static String estasEnCodigoPostal(String numero) {
        return String.format("//button[@data-test-id='geo-sticky'][contains(., '%s')]", numero);
    }
}