import java.util.Random;

public class TrainFactory implements Runnable{
	
	private int instruction; 
	private int ID;
	private Train currentTrain;
	Random random = new Random();
	
	public TrainFactory() {
		createTrain(random.nextInt(2),random.nextInt(20));
	}
	
	public void run() {
		
		while(true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			createTrain(random.nextInt(2),random.nextInt(20));
		}		
		
	}
		
	public synchronized void createTrain(int instruction,int ID) {
		//instruction: create LocalTrain / create ExpressTrain
		//Here is polymorphism,  ExpressTrain and LocalTrain all inherit from Train
		this.ID = ID;
		this.instruction = instruction;
		Train train = null;
		if(instruction==1) {
			
			train = new LocalTrain(ID); 
			
		}else if (instruction==0){
			train = new ExpressTrain(ID); 
		}
		
		currentTrain= train;
	}
	
	public Train getCurrentTrain() {
		return currentTrain;
	}


}
