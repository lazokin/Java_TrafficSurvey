package main;

public class VehicleEvent {
	
	private char lane; // char representing traffic lane
	private int day; // index starting from zero
	private int time; // milliseconds
	private double speed; // meters/second
	
	// static factory method
	public static VehicleEvent create(char lane, int day, int time, double speed) {
		return new VehicleEvent(lane, day, time, speed);
	}
	
	// constructor
	private VehicleEvent(char lane, int day, int time, double speed) {
		this.lane = lane;
		this.day = day;
		this.time = time;
		this.speed = speed;
	}

	// getters
	public char getLane() {
		return lane;
	}
	
	public int getDay() {
		return day;
	}

	public int getTime() {
		return time;
	}

	public double getSpeed() {
		return speed;
	}

	@Override
	public String toString() {
		return lane + " | " + day + " | " + time + " | " + speed;
	}

}
