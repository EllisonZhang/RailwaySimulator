import java.util.Random;

public class TrafficControl implements Runnable{
	
	/** Map[]Array (which include the references to the Objects of all
	 *  the stations and tracks) is the shared object by all the threads!!!!!!!!
	 * */
	
	private TrackSegment[] Map ;
	private Train train;
	private TrackSegment trackSegment;
	Random random = new Random();
     
	
	public TrafficControl(TrackSegment[] map, Train train) {
		this.Map = map;
		this.train = train;
	}
	
	public void run() {
		
		placeTrain();
		
	}
	
	public void placeTrain() {
		
	    int number = random.nextInt(2);
	    if(number == 0 ) {
	    	 Map[0].getTrackCondition().add(8, train.getTrainID()+"");
	    }else {
	    	 Map[1].getTrackCondition().add(8, train.getTrainID()+"");
	    }
  
	        
	}

}
