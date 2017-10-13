package scenario_tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;

public class CreateNewItemFromNonExistentTitleTest {
	
	OutputHandler outputHandler = new OutputHandler();
	String newTitleISBN = "9874567931876";
    public static final int LIBRARIAN = 2;

	@Test
	public void newTitleTest() {
		Output output = outputHandler.createItem(newTitleISBN);
		assertEquals("The Title Does Not Exist!", output.getOutput());
		assertEquals(LIBRARIAN, output.getState());
	}
}
