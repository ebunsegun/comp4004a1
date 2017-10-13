package scenario_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.tables.TitleTable;

public class CreateNewTitleTest {
	
	public static OutputHandler outputHandler = new OutputHandler();
	public static String newTitleInfo = "9874567231876,New beginnigs";
	public static String newISBN = "9874567231876";
    public static final int LIBRARIAN = 2;

	@Test
	public void newTitleTest() {
		Output output = outputHandler.createTitle(newTitleInfo);
		assertEquals("Success!", output.getOutput());
		assertEquals(LIBRARIAN, output.getState());
		assertTrue(TitleTable.getInstance().lookup(newISBN));
	}
}
