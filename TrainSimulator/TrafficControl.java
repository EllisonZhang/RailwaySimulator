import java.util.Random;

public class TrafficControl implements Runnable{
	
	/** Map[]Array (which include the references to the Objects of all
	 *  the stations and tracks) is the shared object by all the threads!!!!!!!!
	 * */
	
	private TrackSegment[] Map ;
	private Train train;
	Random random = new Random();
    private int currentPosition = 0; // the position in Map[]. 
    private int trainSpeed;
	
	public TrafficControl(TrackSegment[] map, Train train) {
		this.Map = map;
		this.train = train;
	}
	
	public void run() {
		
		placeTrain();
		travel();
		
	}
	
	public synchronized void placeTrain() {
		
	    int number = random.nextInt(2);
	    // random chose the start point; 
	    if(number == 0 ) {
	    	 Map[0].getTrackCondition().add(8, train.getTrainID()+",");
	    }else {
	    	 Map[1].getTrackCondition().add(8, train.getTrainID()+",");
	    }
	    currentPosition = number; // update the  current position
	}
	
	public void travel() {
    /* move the train forward.
     * add the current position and update in map
     * */
		while(currentPosition< Map.length) {
			// move the train forward in each iteration			
			this.trainSpeed = train.getSpeed();
			int time = Map[currentPosition].getLength()/trainSpeed + Map[currentPosition].getStopTime() ;				
			try {
				Thread.sleep(time*1000);   
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
			
			// because the position 8 may be replaced by other trains.
			deleteFromCurrentStation();
			currentPosition++;
			
			if(currentPosition< Map.length) {
				moveToNextStatin();
			}	

		}
//		deleteTrain();
	}
	
	public void deleteFromCurrentStation() {
		int index = Map[currentPosition].getTrackCondition().indexOf(train.getTrainID()+","); 
		Map[currentPosition].getTrackCondition().remove(index); //remove from current position
	}
	public void moveToNextStatin() {
		Map[currentPosition].getTrackCondition().add(8, train.getTrainID()+","); //move to next position
	}

}
