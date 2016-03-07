package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import main.EventParser;
import main.RawEvent;
import main.VehicleEvent;

public class EventParserTest {

	@Test
	public void noVehicles() {
		List<RawEvent> rawEvents = new ArrayList<>();
		EventParser eventParser = EventParser.create(rawEvents);
		List<VehicleEvent> vehicleEvents = eventParser.parse();
		assertEquals(0, vehicleEvents.size());
	}
	
	@Test
	public void singleVehicleInLaneA() {
		List<RawEvent> rawEvents = new ArrayList<>();
		rawEvents.add(RawEvent.create('A', 98186));
		rawEvents.add(RawEvent.create('A', 98333));
		EventParser eventParser = EventParser.create(rawEvents);
		List<VehicleEvent> vehicleEvents = eventParser.parse();
		assertEquals(1, vehicleEvents.size());
		VehicleEvent vehicleEvent = vehicleEvents.get(0);
		assertEquals('A', vehicleEvent.getLane());
		assertEquals(0, vehicleEvent.getDay());
		assertEquals(98186, vehicleEvent.getTime());
		assertEquals(16.67f, vehicleEvent.getSpeed(), 1.0f);
	}
	
	@Test
	public void singleVehicleInLaneB() {
		List<RawEvent> rawEvents = new ArrayList<>();
		rawEvents.add(RawEvent.create('A', 638379));
		rawEvents.add(RawEvent.create('B', 638382));
		rawEvents.add(RawEvent.create('A', 638520));
		rawEvents.add(RawEvent.create('B', 638523));
		EventParser eventParser = EventParser.create(rawEvents);
		List<VehicleEvent> vehicleEvents = eventParser.parse();
		assertEquals(1, vehicleEvents.size());
		VehicleEvent vehicleEvent = vehicleEvents.get(0);
		assertEquals('B', vehicleEvent.getLane());
		assertEquals(0, vehicleEvent.getDay());
		assertEquals(638379, vehicleEvent.getTime());
		assertEquals(16.67f, vehicleEvent.getSpeed(), 2.0f);
	}

}
