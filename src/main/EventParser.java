package main;
import java.util.LinkedList;
import java.util.List;

public class EventParser {
	
	private final float SPEED = 16.6667f; // m/s
	private final float AXLE_DISTANCE = 2.5f; // m
	private final float SENSOR_DISTANCE = 0.5f; // m
	private final float TIME_BETWEEN_AXLES_AT_SAME_SENSOR = (AXLE_DISTANCE / SPEED) * 1000; // millis
	private final float TIME_BETWEEN_AXLE_ACROSS_SENSORS = (SENSOR_DISTANCE / SPEED) * 1000; // millis
	
	private final float deviation = 0.2f;

	public List<VehicleEvent> parse(String filePath) {
		
		List<VehicleEvent> events = new LinkedList<>();
		
		return events;
	}
	
	private boolean isSingleVehicleEvent(RawEvent e1, RawEvent e2) {
		float lowerBound = TIME_BETWEEN_AXLES_AT_SAME_SENSOR * (1 - deviation);
		float upperBound = TIME_BETWEEN_AXLES_AT_SAME_SENSOR * (1 + deviation);
		float timeBetweenAxels = e2.getTimestamp() - e1.getTimestamp();
		if (timeBetweenAxels > lowerBound && timeBetweenAxels < upperBound) {
			return true;
		}
		return false;
	}
	
	private VehicleEvent createVehicleEvent(RawEvent e1, RawEvent e2) {
		return null;
	}


}
