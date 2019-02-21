
public class Simulator {
	
    // station objects and track objects are all stored in Map[] Array.
	// both of them implements TrackSegment interface;
	
	private static TrackSegment[] Map = new TrackSegment[7];
	private static String currentMap;
	private TrainFactory trainFactory = new TrainFactory();

	public static void main(String[] args) {
	    
		// initialise
		generateMapElement();
		currentMap = currentMap();
		
		
		
		// simulation part
		ExpressTrain testTrain = new ExpressTrain(16); 
		TrafficControl controller = new TrafficControl (Map,testTrain);
		Thread t = new Thread(controller);
		t.start();
		try {
			t.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	   
		
		// display part
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			System.out.println(Map[0].getTrackCondition().get(8));
//			System.out.println(Map[1].getTrackCondition().get(8)); 
			
			currentMap = currentMap();
			System.out.println(currentMap);
		}
		

	}
	
	
	
	
	
	
	
	public static void generateMapElement() {
		// create 3 segments and 4 stations,  
		// polymorphism was use again in order to easily manage all the element in one Array !!!
        // reference stored in trackArray[] & StationArray[]
		
		for(int i=1; i<Map.length;i+=2) {
			Map[i] = new Track();
		}
		Map[0] = new Station("Glasgow");
		Map[2] = new Station("Stirling");
		Map[4] = new Station("Perth");
		Map[6] = new Station("Inverness");
	}
	
	public static String currentMap() {
		
		//print this to draw the current condition
		currentMap = "";  //refresh Map first
		for(int i=0; i<Map.length;i++) {	
			
			currentMap += Map[i].getCondition();
			
		}
		
		return currentMap;
	}
	

}
