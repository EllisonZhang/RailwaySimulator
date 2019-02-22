import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Station implements TrackSegment {
	
	private final int length = 100;
	private final int stopTime = 5;
	private int stationCapacity = 3;
	private int currentTrainAmount = 0;
	private boolean available = true;
	private String name = "Station";		
	private ArrayList<String> trackCondition = new ArrayList<String>(); //use this to represent the track 
    private String condition;
	private ReentrantLock StaitionLock = new ReentrantLock();
	private Condition roadCondition = StaitionLock.newCondition();
    
   
	public Station(String stationName, int stationCapacity) {
		this.name = stationName;
		this.stationCapacity = stationCapacity;
    	setUpTrack();
    	setCondition();
    }

	public int getCapacity() {
		return stationCapacity;
	}

	public void setStationCapacity(int stationCapacity) {
		this.stationCapacity = stationCapacity;
	}

	public void setUpTrack() {
		trackCondition.add("|");
		for(int i=0;i<4;i++){
			trackCondition.add("-");
		}
		
		trackCondition.add(name);
		
		for(int i=0;i<6;i++) {
			trackCondition.add("-");
		}
		
		trackCondition.add("|");
	}
	
	public void insertTrain(int ID) {
		trackCondition.add(8,""+ID);
	}
	
	public void deleteTrain(int ID) {
		trackCondition.remove(8);
	}
	
	
	/*All the getters and setters below;
	 * */
	public int getStopTime() {
		return stopTime;
	}
	public ArrayList<String> getTrackCondition() {
		return trackCondition;
	}

	public void setTrackCondition(ArrayList<String> trackCondition) {
		this.trackCondition = trackCondition;
	}

	public int getLength() {
		return length;
	}
	 public String getCondition() {
		// condition need to be updated!! current condition
		 
		setCondition(); 
		return condition;
	}

	public void setCondition() {
		condition = "";
		for(int i=0;i<trackCondition.size();i++) {
			condition += trackCondition.get(i);
		}
	}
	public int getCurrentTrainAmount() {
		return currentTrainAmount;
	}

	public void setCurrentcurrentTrainAmount(int currentTrainAmount) {
		this.currentTrainAmount = currentTrainAmount;
	}

	
	public void removeTrain(String trainName) {
		StaitionLock.lock();
		getTrackCondition().remove(trainName);
		currentTrainAmount--;
		
		roadCondition.signalAll();
		StaitionLock.unlock();
		
	}


	public void addTrain(String trainName) {
		StaitionLock.lock();
		
		while(!isAvailable()) {
			try {
				roadCondition.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		getTrackCondition().add(8, trainName);
		currentTrainAmount++;
		StaitionLock.unlock();
	}
	public boolean isAvailable() {
		if(currentTrainAmount<stationCapacity) {
			return true;
		}else {
			return false;
		}	
	}

	
}
