
public abstract class Train {
	
	private int trainID;
	
	public Train(int trainID) {
		setTrainID(trainID);
	}
	
	public int getTrainID() {
		return trainID;
	}
	public void setTrainID(int trainID) {
		this.trainID = trainID;
	} 
	public abstract int getSpeed();
	
}
