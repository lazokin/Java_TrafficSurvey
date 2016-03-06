package main;

public class VehicleEvent {
	
	private char lane;
	private long time;
	private int speed;
	
	public VehicleEvent(char lane, long time, int speed) {
		this.lane = lane;
		this.time = time;
		this.speed = speed;
	}

	public char getLane() {
		return lane;
	}

	public long getTime() {
		return time;
	}

	public int getSpeed() {
		return speed;
	}

	@Override
	public String toString() {
		return lane + " | " + time + " | " + speed;
	}
	
	
	
}
