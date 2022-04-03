package com.megahack.ateam.transcript.util;

import com.google.gson.JsonObject;

import java.util.ArrayList;

public class Word {
  private String word;
  private double startTime, endTime, duration, timeToNextWord;
  
  public Word(String word, double startTime, double endTime) {
    this.word = word;
    this.startTime = startTime;
    this.endTime = endTime;
    this.duration = Double.parseDouble(String.format("%3f", Math.abs(endTime - startTime)));
  }
  
  public String getWord() {
    return word;
  }
  
  public double getStartTime() {
    return startTime;
  }
  
  public double getEndTime() {
    return endTime;
  }
  
  public double getDuration() {
    return duration;
  }
  
  public double getTimeToNextWord() {
    return timeToNextWord;
  }
  
  public void setTimeToNextWord(double timeToNextWord) {
    this.timeToNextWord = timeToNextWord;
  }
  
  public JsonObject toJson() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("word", word);
    jsonObject.addProperty("startTime", startTime);
    jsonObject.addProperty("endTime", endTime);
    jsonObject.addProperty("duration", duration);
    
    return jsonObject;
  }
  
  @Override
  public String toString() {
    return "Word{" +
            "word='" + word + '\'' +
            ", startTime=" + startTime +
            ", endTime=" + endTime +
            ", duration=" + duration +
            ", timeToNextWord=" + timeToNextWord +
            '}';
  }
  
  public ArrayList<String> getTranscript(ArrayList<Word> words){
    ArrayList<String> transcript = new ArrayList<>();
    for(Word word1 : words) {
      transcript.add(word1.getWord());
    }
    return transcript;
  }
}

/*
hello everyone, my name is avi gupta.
I am going to show you a demo of the AInnotations transcribe program.
What it does is takes an audio recording and gives a transcription of it.
This "transcription" is a JSON file, which shows the full transcription
As well as a detailed view of every word.

I am going to use Postman for this demo, as it allow me to create an HTTP post
To the local server in which the program is running in.

I am going to upload a mono .WAV file to the box, and send it to the server. This
.WAV file contains me saying a quote about deadlines.
The process takes a couple of moments, depending on how big the file is.


[Waits]
So as you can see, the program shows that job ID and blob ID, which are ways
Of tracking the program. It also shows the full transcript as well as the words,
With time info for each word.

Thank you for your time!
 */