# Fravega Automation Framework

Framework de automatización para el sitio web de Frávega, desarrollado en Java con Selenium, Cucumber y TestNG.

## Tecnologías

- Java + Selenium
- Cucumber + TestNG
- Maven
- ExtentReports

## Estructura

```
src/test/
├── java/pages           # Page Objects
├── java/utils           # Utilidades: driver, reportes, config
├── java/steps           # Definición de steps
├── java/runner          # Test Runner
└── resources/features   # Archivos .feature
```

## Ejecución

```bash
mvn test -Dcucumber.filter.tags="@Fravega" -Dbrowser=chrome
```
o desde runner/TestRunner.java editando las variables de ambiente. 

## Reporte

- **Extent Report**: `extent-report/ExtentReport.html`
#### Abrir el HTML que se genera en extent-report/ExtentReport.html

## Autor

**Paula Carluccio** - [GitHub](https://github.com/PaulaCarluccio)