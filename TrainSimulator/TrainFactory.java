import java.util.Random;

public class TrainFactory implements Runnable{
	
	private int instruction; 
	private int ID;
	private Train currentTrain;
	Random random = new Random();
	private TrackSegment[] Map ;
	public TrainFactory(TrackSegment[] Map) {
		createTrain(random.nextInt(2),random.nextInt(20));
		this.Map = Map;
	}
	
	public void run() {
		
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			createTrain(random.nextInt(2),random.nextInt(200));
		}		
		
	}
		
	public synchronized void createTrain(int instruction,int ID) {
		//instruction: create LocalTrain / create ExpressTrain
		//Here is polymorphism,  ExpressTrain and LocalTrain all inherit from Train
		this.ID = ID;
		this.instruction = instruction;
		Train train = null;
		if(instruction==1) {
			
			train = new Train(ID,Map,10); 
			
		}else if (instruction==0){
			train = new Train(ID,Map,500); 
		}
		
		currentTrain= train;
	}
	
	public Train getCurrentTrain() {
		return currentTrain;
	}


}
