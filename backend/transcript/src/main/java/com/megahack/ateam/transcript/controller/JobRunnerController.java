package com.megahack.ateam.transcript.controller;

import com.megahack.ateam.transcript.msg.Body;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobRunnerController {
  
  @PostMapping(value = "/submitJob", produces = "text/plain")
  public ResponseEntity<String> handleJob(@RequestBody Body body) {
    
    
    
    return ResponseEntity.ok("ok");
  }
  
  
}
