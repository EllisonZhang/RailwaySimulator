
public class TrainFactory implements Runnable{
	
	private String instruction; 
	private int ID;
	private Train currentTrain;
	
	public TrainFactory() {
		
	}
	
	public void run() {
		
		currentTrain = createTrain(instruction,ID);
		
	}
		
	public synchronized Train createTrain(String instruction,int ID) {
		//instruction: create LocalTrain / create ExpressTrain
		//Here is polymorphism,  ExpressTrain and LocalTrain all inherit from Train
		this.ID = ID;
		this.instruction = instruction;
		Train train = null;
		if(instruction.equals("createLocal")) {
			
			train = new LocalTrain(ID); 
			
		}else if (instruction.equals("createExpress")){
			train = new ExpressTrain(ID); 
		}
		
		return train;
	}
	
	public synchronized Train getCurrentTrain() {
		return currentTrain;
	}


}
