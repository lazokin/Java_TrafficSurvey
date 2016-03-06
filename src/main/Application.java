package main;
import java.io.File;
import java.util.List;

public class Application {

	public static void main(String[] args) {
		
		File file = new File("data.txt");
		
		// parse data
		FileParser parser = FileParser.create(file);
		List<RawEvent> rawEvents = parser.parse();
		
	}

}
