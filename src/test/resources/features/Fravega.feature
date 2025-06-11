@Fravega
  Feature: Cambio de ubicación por código postal

  Scenario: El usuario modifica su ubicación ingresando un nuevo código postal
    Given el usuario accede al sitio web de Frávega
    Then se visualiza el popup con el mensaje "Ingresá tu ubicación"
    When se ingresa el código postal "1206"
    And hace clic en el botón Guardar
    Then el popup se cierra correctamente
    And se visualiza un mensaje confirmando el cambio de ubicación
