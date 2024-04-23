package com.unibank.unitech.common;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.springframework.core.io.ClassPathResource;

public class TestUtil {

    private TestUtil() {
    }

    public static String json(String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource("/files/" + fileName);
        try (InputStream inputStream = resource.getInputStream()) {
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
