import java.util.Random;

public class Simulator {
	
    // station objects and track objects are all stored in Map[] Array.
	// both of them implements TrackSegment interface;
	
	private static TrackSegment[] Map = new TrackSegment[7];
	private static String currentMap;
	private static TrainFactory trainFactory = new TrainFactory();

	public static void main(String[] args) {
	    
//***** initialise
		generateMapElement();
		currentMap = currentMap();
		System.out.println(currentMap);
	
//**** 	produce train thread *****//
		
	
		Thread creatTrainThread = new Thread(trainFactory);
		creatTrainThread.start();
		
////***** display section --- main thread ******//
//		Simulator simulator = new Simulator();
//		Display display = simulator.new Display();
//		Thread displayThread = new Thread(display);
//		displayThread.start();		
		
//**** simulation part --- train threads ******//
		int numberOfTrains = 6;	
		Thread[] threads = new Thread[numberOfTrains];
		Train[] trains = new Train[numberOfTrains];
		
		for (int i=0;i<numberOfTrains;i++) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			trains[i] = trainFactory.getCurrentTrain(); 
			threads[i] = new Thread(new TrafficControl (Map,trains[i]));
			threads[i].start();
			System.out.println("Train Speed is:" + trains[i].getSpeed());
		}
		
//		Train testTrain1 = trainFactory.getCurrentTrain(); 
//		TrafficControl controller1 = new TrafficControl (Map,testTrain1);
//		Thread t1 = new Thread(controller1);
//		t1.start();
//		System.out.println(testTrain1.getSpeed());
		
	   
		
//***** display section --- main thread ******//
//		Simulator simulator = new Simulator();
//		Display display = simulator.new Display();
//		Thread displayThread = new Thread(display);
//		displayThread.start();
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			currentMap = currentMap();
			System.out.println(currentMap);
		}
		

	}
	
	
	
	class Display implements Runnable {
			
		public void run() {
			while(true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
				currentMap = currentMap();
				System.out.println(currentMap);
			}
			
		}
	}
	
	
	
	public static void generateMapElement() {
		// create 3 segments and 4 stations,  
		// polymorphism was use again in order to easily manage all the element in one Array !!!
        // reference stored in trackArray[] & StationArray[]
		
		for(int i=1; i<Map.length;i+=2) {
			Map[i] = new Track();
		}
		Map[0] = new Station("Glasgow",3);
		Map[2] = new Station("Stirling",2);
		Map[4] = new Station("Perth",2);
		Map[6] = new Station("Inverness",3);
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
