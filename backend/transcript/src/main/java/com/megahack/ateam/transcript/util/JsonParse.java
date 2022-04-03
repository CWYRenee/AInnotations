package com.megahack.ateam.transcript.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class JsonParse {
  public ArrayList<Word> processTranscription(String filePath) throws IOException {
    
    ArrayList<Word> words = new ArrayList<Word>();
    
    byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));
    String jsonString = new String(fileBytes, StandardCharsets.UTF_8);
    JsonObject output = JsonParser.parseString(jsonString).getAsJsonObject();
    JsonArray results = output.getAsJsonArray("results");
    
    
    float endTime, startTime = 0, duration;
    String transcript = "";
    
    for(JsonElement result : results) {
      JsonArray alternatives = result.getAsJsonObject().getAsJsonArray("alternatives");
      for(JsonElement alternative : alternatives) {
        JsonObject holder = alternative.getAsJsonObject();
        if(holder.has("words")) {
          //transcript = holder.get("transcript").getAsString();
          
          JsonArray wordsArray = holder.getAsJsonArray("words");
          for(JsonElement word : wordsArray) {
            JsonObject wordObject = word.getAsJsonObject();
            if(wordObject.has("word") &&
                    wordObject.has("startTime") &&
                    wordObject.has("endTime")) {
              String wordString = wordObject.get("word").getAsString();
              String startTimeString = wordObject.get("startTime").getAsString();
              String endTimeString = wordObject.get("endTime").getAsString();
              
              startTime = Float.parseFloat(startTimeString.substring(0, startTimeString.length() - 1));
              endTime = Float.parseFloat(endTimeString.substring(0, endTimeString.length() - 1));
              words.add(new Word(wordString, startTime, endTime));
            }
          }
        }
      }
    }
    calculateTimeToNextWord(words);
    return words;
  }
  
  private void calculateTimeToNextWord(ArrayList<Word> words) {
    for(int i = 0; i < words.size(); i++) {
      if(i == words.size() - 1) {
        words.get(i).setTimeToNextWord(0.0);
      } else {
        words.get(i).setTimeToNextWord(words.get(i + 1).getStartTime() - words.get(i).getEndTime());
      }
    }
  }
}