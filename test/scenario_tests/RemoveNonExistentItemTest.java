package scenario_tests;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;

public class RemoveNonExistentItemTest {
	OutputHandler outputHandler = new OutputHandler();
	String nonExistentTitleISBN = "9781487667777,1";
    public static final int LIBRARIAN = 2;
 
	@Test
	public void nonExistingItemTest() {
		Output output = outputHandler.removeItem(nonExistentTitleISBN);
		assertEquals("The Item Does Not Exist!", output.getOutput());
		assertEquals(LIBRARIAN, output.getState());
	}
}
