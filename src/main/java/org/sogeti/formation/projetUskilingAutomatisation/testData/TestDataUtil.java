package org.sogeti.formation.projetUskilingAutomatisation.testData;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class TestDataUtil {

    public static JsonNode loadTestData(String fileDataPath) {
        try (InputStream input = TestDataUtil.class.getClassLoader().getResourceAsStream(fileDataPath)) {
            if (input != null) {
                String jsonContent = IOUtils.toString(input, "UTF-8");
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readTree(jsonContent);
            } else {
                throw new RuntimeException("Test data file not found: " + fileDataPath);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading testData file: " + e.getMessage(), e);
        }
    }
}
