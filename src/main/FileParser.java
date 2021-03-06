package main;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FileParser {
	
	File file;

	public static FileParser create(File file) {
		return new FileParser(file);
	}
	
	private FileParser(File file) {
		this.file = file;
	}

	public List<RawEvent> parse() {
		
		List<RawEvent> events = new LinkedList<>();

		try (Scanner input = new Scanner(file)) {
			while(input.hasNext()) {
				events.add(parseLine(input.nextLine()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return events;
	}
	
	private RawEvent parseLine(String line) throws Exception {
		String laneStr = line.substring(0, 1);
		String timeStr = line.substring(1);
		char lane = laneStr.charAt(0);
		int timestamp = Integer.parseInt(timeStr);
		return RawEvent.create(lane, timestamp);
	}


}
