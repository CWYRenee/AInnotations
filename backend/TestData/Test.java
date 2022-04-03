import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

// class TestData{
	
// 	static{
//     ArrayList<Integer> references = new ArrayList<>();
//     references.add(/*Important nums*/)
// 		test.put("/MachineLearning/Machine_Learning_Reading.wav", references);
// 	}
// }
class Test{
	//Fields: Txt, Audio, JSON, ArrayList of Words, Number of Words, ArrayList indexes, Double[] NetInput
	static Hashtable<String ,Test> testData = new Hashtable<String, Test>();
	public JsonObject transcriptInfo;
	public File file;  
	public Audio audio; 
	public ArrayList<String> words;  
	public int numWords;
	public ArrayList<Integer> importantIndexes;
	public double[] netInput;
	public int[] expOutput;

	public void defaultTests(){
		testData.put("Machine Learning", new Test(new File("/MachineLearning/MachineLearning.txt"),
												  new Audio("/MachineLearning/Machine_Learning_Reading.wav")
												  new JSONObject();
												  new int[]{8, 15, 18, 19, 20, 28, 29, 30, 31, 32, 34, 35, 36, 37, 38, 39, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241 , 245, 246, 247, 248, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322});
	}
	public Test(File f, Audio a, JsonObject j, int[] iia){
    	file = f;
    	audio = a;
    	transcriptInfo = j;
		words = new ArrayList<String>();
		//Call JSON
    	numWords = words.size();
      	int [] importIndexArr = iia;
    	imporantIndexes = new ArrayList<Integer>(Arrays.asList(importIndexArr));
		
    	ArrayList<Double> netInputL = new ArrayList<Double>();
    	for(int i = 0; i<transcript.size(); i++){
			netInputL.add((new ArrayList<String> (Arrays.asList("important","write","remember","note","example",
				"essentially", "basically", "defined"))).contains(transcript.get(i).word ? 1.0 : 0.0);
			netInputL.add(transcript.get(i).pitch);
			netInputL.add(transcript.get(i).volume);	              
			netInputL.add(transcript.get(i).duration/transcript.get(i).word.length());
			netInputL.add(transcript.get(i).timeToNextWord);
		}
		netInput = netInputL.stream().mapToDouble(d -> d).toArray();
		expOutput = new int[numWords];
		for(int i=0; i<numWords) expOutput[i] = (importantIndexes.contains(i) ? 1:0);
    }

  public ArrayList<String> getTranscript(ArrayList<Word> words){
    ArrayList<String> transcript = new ArrayList<>()
    for(Word word1 : words) {
      transcript.add(word1.getWord());
    }
    return transcript;
  }
}