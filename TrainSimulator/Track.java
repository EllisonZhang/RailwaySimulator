import java.util.ArrayList;

public class Track implements TrackSegment{
	
	private final int length = 1000;
	private final int stopTime = 0; // doesn't stop on the track!
	
	
	private String name = "track";
	
	//use this to represent the track 
	private ArrayList<String> trackCondition = new ArrayList<String>();
    private String condition = "";
    
   
	public Track() {
    	setUpTrack();
    	setCondition();
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
	
	
}
