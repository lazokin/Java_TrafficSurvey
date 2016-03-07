package main;

public class RawEvent {
	
	private char lane; // char representing traffic lane
	private int time; // milliseconds
	
	// static factory method
	public static RawEvent create(char lane, int time) {
		return new RawEvent(lane, time);
	}
	
	// constructor
	private RawEvent(char lane, int time) {
		this.lane = lane;
		this.time = time;
	}

	// getters
	public char getLane() {
		return lane;
	}

	public int getTime() {
		return time;
	}
	
	@Override
	public String toString() {
		return lane + " | " + time;
	}
	
}
