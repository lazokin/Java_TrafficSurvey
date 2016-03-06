package main;

public class RawEvent {
	
	public enum LaneType {
		A, B
	}
	
	private LaneType laneType;
	private int timestamp;
	
	private RawEvent(LaneType laneType, int timestamp) {
		this.laneType = laneType;
		this.timestamp = timestamp;
	}

	public LaneType getLaneType() {
		return laneType;
	}

	public int getTimestamp() {
		return timestamp;
	}
	
	public static RawEvent create(LaneType laneType, int timestamp) {
		return new RawEvent(laneType, timestamp);
	}
	
}
