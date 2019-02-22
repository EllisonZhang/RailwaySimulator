import java.util.ArrayList;

public interface TrackSegment{
	
	public void setUpTrack();
	public String getCondition();
	public ArrayList<String> getTrackCondition();
	public int getLength();
	public int getStopTime();
	public void removeTrain(String trainName);
	public void addTrain(String trainName);
	public boolean isAvailable();
}
