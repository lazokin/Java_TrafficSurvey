package main;
import java.io.File;
import java.util.List;

public class Application {

	public static void main(String[] args) {
		
		// use hard-coded data file
		File file = new File("data.txt");
		
		// parse file and create raw events
		FileParser fileParser = FileParser.create(file);
		List<RawEvent> rawEvents = fileParser.parse();
		
		// parse raw events and create vehicle events
		EventParser eventParser = EventParser.create(rawEvents);
		List<VehicleEvent> vehicleEvents = eventParser.parse();
		
		// list vehicleEvents
		for (VehicleEvent vehicleEvent : vehicleEvents) {
			System.out.println(vehicleEvent.toString());
		}
	}

}
