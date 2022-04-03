package com.megahack.ateam.transcript.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvConfig {
  
  public String getBucket() {
    return "megahack-audio-bucket";
  }
  
  public String getTopic() {
    return "transcribe";
  }
}
