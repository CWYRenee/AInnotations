public class Word {
    public String word;
    public double startTime=0, endTime=0, duration=0, timeToNextWord=0, pitch=0, volume=0;
	public static Word currentWord;
	
    public Word(String word, double startTime, double endTime) {
        this.word = word;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = Math.abs(endTime - startTime);
		currentWord = this;
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
    
    
}