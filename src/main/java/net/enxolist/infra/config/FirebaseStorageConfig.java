package net.enxolist.infra.config;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseStorageConfig {

    @Bean
    public Storage googleStorage() throws IOException {
        ClassPathResource resource = new ClassPathResource("firebase-private-key.json");
        try (InputStream is = resource.getInputStream()) {
            Credentials credentials = GoogleCredentials.fromStream(is);
            return StorageOptions.newBuilder()
                    .setCredentials(credentials)
                    .build()
                    .getService();
        }
    }
}