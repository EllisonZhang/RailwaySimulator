
public class LocalTrain extends Train{
	
private final int speed = 200;
	
	public LocalTrain(int trainID) {
		super(trainID);
	}
	
	public int getSpeed() {
		return speed;
	}

}
