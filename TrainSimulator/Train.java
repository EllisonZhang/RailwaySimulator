import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Train implements Runnable {
	
	private int speed = 0;

	private TrackSegment[] Map ;
	Random random = new Random();
    private int currentPosition = 0; // the position in Map[]. 
    int time = 0;
    private String trainName;
	
    
	public Train(int trainID,TrackSegment[] map,int speed) {
		this.Map = map;
		this.speed = speed;
		trainName = trainID + ",";
//		conditionGenerate();
	}
	
	
	public void run() {
		
		placeTrain();
		travel();
		
	}
	
	
	public void placeTrain() {
	
	    int number = random.nextInt(2);
	    // random chose the start point; 
	    if(number == 0 ) {
	    	 Map[0].addTrain(trainName);
	    }else {
	    	 Map[1].addTrain(trainName);
	    }
	    currentPosition = number; // update the  current position
	}
	
	public void travel() {
	    /* move the train forward.
	     * add the current position and update in map
	     * */
			while(currentPosition< Map.length) {
				// move the train forward in each iteration			
				time = Map[currentPosition].getLength()/speed + Map[currentPosition].getStopTime() ;				
				try {
					Thread.sleep(time*1000);   
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
				
				deleteFromCurrentStation();
			
				currentPosition++; // ready to move!!! 
									
				if(currentPosition< Map.length) {

					moveToNextStatin();
				}

			}

		}
	
	
	public void deleteFromCurrentStation() {
		Map[currentPosition].removeTrain(trainName); // remove train number from station
	}
	
	public void moveToNextStatin() {
		Map[currentPosition].addTrain(trainName); // remove train number from station	
	}
	
	public int getSpeed() {
		return speed;
	}
	
	
}
