package main;
import java.util.LinkedList;
import java.util.List;

public class EventParser {
	
	private final double AXLE_DISTANCE = 2.5; // m
	
	List<RawEvent> rawEvents;

	public static EventParser create(List<RawEvent> rawEvents) {
		return new EventParser(rawEvents);
	}
	
	private EventParser(List<RawEvent> rawEvents) {
		this.rawEvents = rawEvents;
	}

	public List<VehicleEvent> parse() {
		
		int day = 0;
		
		RawEvent lastRawEvent = null;
		List<VehicleEvent> vehicleEvents = new LinkedList<>();
		
		while (!rawEvents.isEmpty()) {
			
			RawEvent re1 = rawEvents.get(0);
			RawEvent re2 = rawEvents.get(1);
			
			if (lastRawEvent != null) {
				if (re1.getTime() < lastRawEvent.getTime()) {
					day++;
				}
			}
			
			if (isSingleVehicleEventAcrossOneHose(re1, re2)) {
				vehicleEvents.add(createSingleVehicleEvent(re1, re2, day));
				lastRawEvent = re2;
				rawEvents.remove(0);
				rawEvents.remove(0);
				continue;
			}
			
			RawEvent re3 = rawEvents.get(2);
			RawEvent re4 = rawEvents.get(3);
			
			if (isSingleVehicleEventAcrossBothHoses(re1, re2, re3, re4)) {
				vehicleEvents.add(createSingleVehicleEvent(re1, re2, re3, re4, day));
				lastRawEvent = re4;
				rawEvents.remove(0);
				rawEvents.remove(0);
				rawEvents.remove(0);
				rawEvents.remove(0);
				continue;
			}
			
		}
		
		return vehicleEvents;
	}
	
	private boolean isSingleVehicleEventAcrossOneHose(RawEvent e1, RawEvent e2) {
		return sameLane(e1, e2);
	}
	
	private boolean isSingleVehicleEventAcrossBothHoses(RawEvent e1, RawEvent e2, RawEvent e3, RawEvent e4) {
		return sameLane(e1, e3) && sameLane(e2, e4);
	}
	
	private VehicleEvent createSingleVehicleEvent(RawEvent e1, RawEvent e2, int day) {
		char lane = e1.getLane();
		int time = e1.getTime();
		double speed = calculateSpeed(e1, e2);
		return VehicleEvent.create(lane, day, time, speed);
	}
	
	private VehicleEvent createSingleVehicleEvent(RawEvent e1, RawEvent e2, RawEvent e3, RawEvent e4, int day) {
		char lane = e2.getLane();
		int time = e1.getTime();
		double speed = calculateSpeed(e1, e3);
		return VehicleEvent.create(lane, day, time, speed);
	}
	
	private boolean sameLane(RawEvent e1, RawEvent e2) {
		return e1.getLane() == e2.getLane();
	}
	
	// calculate speed from raw events (m/s)
	private double calculateSpeed(RawEvent e1, RawEvent e2) {
		return (AXLE_DISTANCE / (e2.getTime() - e1.getTime())) * 1000;
	}

}
