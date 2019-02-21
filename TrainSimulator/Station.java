import java.util.ArrayList;

public class Station implements TrackSegment {
	
	private final int length = 100;

    private String name = "Station";
	
	//use this to represent the track 
	private ArrayList<String> trackCondition = new ArrayList<String>();
    private String condition;
    
   
	public Station(String stationName) {
		this.name = stationName;
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
