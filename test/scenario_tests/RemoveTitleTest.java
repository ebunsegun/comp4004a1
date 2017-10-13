package scenario_tests;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;

public class RemoveTitleTest {
	public static OutputHandler outputHandler = new OutputHandler();
	public static String title = "Sky Scraper";
	public static String ISBN = "9001889066666";
	public static String existingTitleInfo = ISBN + "," + title;
    public static final int LIBRARIAN = 2;

    @Before
    public void setup() {
    	outputHandler.createTitle(existingTitleInfo);
    }
    
	@Test
	public void existingTitleTest() {
		Output output = outputHandler.removeTitle(ISBN);
		assertEquals("Success!", output.getOutput());
		assertEquals(LIBRARIAN, output.getState());
	}
}
