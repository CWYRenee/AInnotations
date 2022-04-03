package com.megahack.ateam.transcript.config;

import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class AppConfig {
  
  @Bean
  public SpeechClient speechClient() throws IOException {
    return SpeechClient.create();
  }
  
  @Bean
  public Storage getStorage() throws IOException {
    return StorageOptions.getDefaultInstance().getService();
  }
}
