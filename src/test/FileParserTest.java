package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.junit.*;

import main.FileParser;
import main.RawEvent;

public class FileParserTest {
	
	static File file;
	static FileWriter fw;
	static FileReader fr;
	static FileParser parser;
	
	@Before
	public void before() {
		try {
			file = new File("src/test/data.txt");
			fw = new FileWriter(file);
			fr = new FileReader(file);
			parser = FileParser.create(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void after() {
		try {
			file.delete();
			fw.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void parseNoRawEvents() throws IOException {
		List<RawEvent> events = parser.parse();
		assertEquals(0, events.size());
	}
	
	@Test
	public void parseOneRawEventOfTypeA() throws IOException {
		fw.write("A12345\n");
		fw.flush();
		List<RawEvent> events = parser.parse();
		assertEquals(1, events.size());
		RawEvent event = events.get(0);
		assertEquals(event.getLaneType(), RawEvent.LaneType.A);
		assertEquals(event.getTimestamp(), 12345);
	}
	
	@Test
	public void parseTwoRawEventsOfTypeB() throws IOException {
		fw.write("B12345\n");
		fw.write("B98765\n");
		fw.flush();
		List<RawEvent> events = parser.parse();
		assertEquals(2, events.size());
		RawEvent e1 = events.get(0);
		assertEquals(e1.getLaneType(), RawEvent.LaneType.B);
		assertEquals(e1.getTimestamp(), 12345);
		RawEvent e2 = events.get(1);
		assertEquals(e2.getLaneType(), RawEvent.LaneType.B);
		assertEquals(e2.getTimestamp(), 98765);
	}

}
