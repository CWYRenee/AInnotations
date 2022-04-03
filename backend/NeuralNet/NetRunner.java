import java.util.ArrayList;
import java.util.List;



class NetTrainer {	

	static int maxWords;
	static double[][] X; 
	static double[][] Y; 
	public NetTrainer(Test t1, Test t2, Test t3, Test t4, String name) {	
		maxWords = Math.max(Math.max(t1.numWords,t2.numWords),Math.max(t3.numWords,t4.numWords));
		
		X = new double[][]{ addZeros(maxWords, Test.testData.get(t1).netInput, true),
							addZeros(maxWords, Test.testData.get(t2).netInput, true),
							addZeros(maxWords, Test.testData.get(t3).netInput, true),
							addZeros(maxWords, Test.testData.get(t4).netInput, true)};

		Y = new double[][]{ addZeros(maxWords, Test.testData.get(t1).expOutput, false),
							addZeros(maxWords, Test.testData.get(t2).expOutput, false),
							addZeros(maxWords, Test.testData.get(t3).expOutput, false),
							addZeros(maxWords, Test.testData.get(t4).expOutput, false)};
	
		NeuralNetwork nn = new NeuralNetwork(maxWords*5,maxWords*3,maxWords);
		
		nn.fit(X, Y, 50000);

		//@ToDo Add Net to JSON file with name & nn
			
	}
	public double[] addZeros(int size, double[] data, boolean input){
		if(input) size*=5;
		double[] r = new double[size];
		for(int i=0;i<size;i++){
			if(i>=data.length) r[i] = 0.0;
			else r[i] = data[i];
		}
		return r;
	}
}