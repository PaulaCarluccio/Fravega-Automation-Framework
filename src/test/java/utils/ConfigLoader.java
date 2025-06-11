package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private final Properties properties = new Properties();

    public ConfigLoader(String datasetName) {
        loadProperties(datasetName);
    }

    private void loadProperties(String datasetName) {
        String path = "src/test/resources/config/" + datasetName + ".properties";
        try (FileInputStream fis = new FileInputStream(path)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo cargar el archivo de configuración: " + path, e);
        }
    }
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}