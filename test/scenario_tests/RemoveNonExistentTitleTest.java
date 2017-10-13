package scenario_tests;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;

public class RemoveNonExistentTitleTest {
	public static OutputHandler outputHandler = new OutputHandler();
	public static String title = "Broken Glass";
	public static String ISBN = "9001889066777";
	public static String existingTitleInfo = ISBN + "," + title;
    public static final int LIBRARIAN = 2;
	
	@Test
	public void nonExistingTitleTest() {
		Output output = outputHandler.removeTitle(ISBN);
		assertEquals("The Title Does Not Exist!", output.getOutput());
		assertEquals(LIBRARIAN, output.getState());
	}

}
