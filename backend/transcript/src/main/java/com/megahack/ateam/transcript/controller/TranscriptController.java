package com.megahack.ateam.transcript.controller;

import com.google.api.gax.longrunning.OperationFuture;
import com.google.cloud.ServiceOptions;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.cloud.speech.v1.*;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.longrunning.Operation;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;
import com.megahack.ateam.transcript.config.EnvConfig;
import com.megahack.ateam.transcript.util.Word;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class TranscriptController {
  
  private final Gson gson = new Gson();
  
  private final SpeechClient speechClient;
  private final Storage storage;
  private final EnvConfig envConfig;
  
  private final Publisher publisher;
  
  public TranscriptController(SpeechClient speechClient, Storage storage, EnvConfig envConfig) throws IOException {
    this.speechClient = speechClient;
    this.storage = storage;
    this.envConfig = envConfig;
  
    ProjectTopicName topicName =
            ProjectTopicName
                    .newBuilder()
                    .setProject(ServiceOptions.getDefaultProjectId())
                    .setTopic(envConfig.getTopic())
                    .build();
    
    this.publisher = Publisher.newBuilder(topicName).build();
  }
  
  @GetMapping(value = "/time", produces = "application/json")
  @ResponseBody
  public ResponseEntity<String> getTime() {
    Date date = new Date();
    JsonObject response = new JsonObject();
    response.addProperty("date", date.toString());
    response.addProperty("time", date.getTime());
    return ResponseEntity.ok(response.toString());
  }
  
  @PostMapping(value = "/sound", produces = "application/json")
  @ResponseBody
  public ResponseEntity<String> postSound(@RequestBody byte[] sound) {
    JsonObject response = new JsonObject();
    response.addProperty("length", sound.length);
    
    String blobName = "" + System.currentTimeMillis();
  
    BlobId blobId = BlobId.of(envConfig.getBucket(), blobName);
    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
    storage.create(blobInfo, sound);
    
    String gcsUri = "gs://" + envConfig.getBucket() + "/" + blobName;
    System.out.println(">> >> input blob: " + gcsUri);
    
    response.addProperty("blobName", blobName);
    
    try {
      ByteString audioBytes = ByteString.copyFrom(sound);
      RecognitionConfig config =
              RecognitionConfig.newBuilder()
                      .setLanguageCode("en-US")
                      .setEnableWordConfidence(true)
                      .setEnableWordTimeOffsets(true)
                      .build();
      RecognitionAudio audio = RecognitionAudio.newBuilder().setUri(gcsUri).build();
  
      OperationFuture<LongRunningRecognizeResponse, LongRunningRecognizeMetadata> operationFutureResponse =
              speechClient.longRunningRecognizeAsync(config, audio);
  
      String jobName = operationFutureResponse.getName();
      System.out.println(">> >> jobName = " + jobName);
      response.addProperty("jobName", jobName);

      while(! operationFutureResponse.isDone()) {
        System.out.println(">> >> ["+System.currentTimeMillis()+"]waiting for job = " + operationFutureResponse.getName());
        Thread.sleep(2000);
      }
  
      JsonArray responseResults = new JsonArray();
  
      List<SpeechRecognitionResult> results = operationFutureResponse.get().getResultsList();
  
      JsonArray wordJsonArray = new JsonArray();
      
      for (SpeechRecognitionResult result : results) {
        // There can be several alternative transcripts for a given chunk of speech. Just use the
        // first (most likely) one here.
        SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
//        System.out.println("alternative = " + alternative);
        responseResults.add(alternative.getTranscript());
        
        List<WordInfo> wordsList = alternative.getWordsList();
        
        System.out.println("wordsList size = " + wordsList.size());
        for(WordInfo wordInfo : wordsList) {
          double startTime = wordInfo.getStartTime().getSeconds() + (wordInfo.getStartTime().getNanos() * 1.0/1000000000.0);
          double endTime = wordInfo.getEndTime().getSeconds() + (wordInfo.getEndTime().getNanos() * 1.0/1000000000.0);
          Word word = new Word(
                  wordInfo.getWord(),
                  startTime,
                  endTime
          );
          
          System.out.println("wordInfo = " + wordInfo);
          System.out.println("word = " + word);
          wordJsonArray.add(word.toJson());
        }
      }
  
      
  
      response.add("results", responseResults);
      response.add("words", wordJsonArray);
    }
    catch(ExecutionException ex) {
      ex.printStackTrace();
    }
    catch(InterruptedException ex) {
      ex.printStackTrace();
    }
  
    return ResponseEntity.ok(response.toString());
  }
  
  public static void main(String[] args) {
  
  }
  
/*
  @GetMapping(value = "/job/{jobID}")
  public ResponseEntity<String> getJobStatus(@PathVariable String jobID){
    JsonObject response = new JsonObject();
    Operation operation = speechClient.getOperationsClient().getOperation(jobID);
    if(operation.getDone()) {
      if(operation.hasError()) {
        response.addProperty("status", "error");
        response.addProperty("error", operation.getError().getMessage());
      }
      else {
        operation.getResponse();
      }
    }
    else {
      response.addProperty("status", "pending");
    }
    return ResponseEntity.ok(response.toString());
  }
*/

  

}
