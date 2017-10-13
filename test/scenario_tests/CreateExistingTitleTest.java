package scenario_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.tables.TitleTable;

public class CreateExistingTitleTest {
	
	public static OutputHandler outputHandler = new OutputHandler();
	public static String existingBookTitle = "Far Away From Home";
	public static String existingISBN = "9001889065467";
	public static String existingTitleInfo = existingISBN + "," + existingBookTitle;
    public static final int LIBRARIAN = 2;

    @Before
    public void setup() {
    	outputHandler.createTitle(existingTitleInfo);
    }
    
	@Test
	public void existingTitleTest() {
		Output output = outputHandler.createTitle(existingTitleInfo);
		assertEquals("The Title Already Exists!", output.getOutput());
		assertEquals(LIBRARIAN, output.getState());
		assertTrue(TitleTable.getInstance().lookup(existingISBN));
	}
}
