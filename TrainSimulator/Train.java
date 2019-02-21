
public abstract class Train {
	
	private int trainID;
	
	public Train(int trainID) {
		setTrainID(trainID);
	}
	
	public synchronized int getTrainID() {
		return trainID;
	}
	public synchronized void setTrainID(int trainID) {
		this.trainID = trainID;
	} 
	

}
