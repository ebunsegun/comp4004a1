package scenario_tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;

public class CreateExistingItemTest {
	
	public static OutputHandler outputHandler = new OutputHandler();
	public static String titleInfo = "9879898765632,Midnight Train";
	public static String ISBN = "9879898765632";
    public static final int LIBRARIAN = 2;
    
    @Before
    public void setup() {
    	outputHandler.createTitle(titleInfo);
    }

	@Test
	public void existingTitleTest() {
		Output output = outputHandler.createItem(ISBN);
		assertEquals("Success!", output.getOutput());
		assertEquals(LIBRARIAN, output.getState());
	}
}
