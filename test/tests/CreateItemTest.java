package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.tables.ItemTable;
import server.logic.tables.TitleTable;

public class CreateItemTest {
	
	OutputHandler outputHandler = new OutputHandler();
	String existingTitleISBN = "9781442668584";
	String newTitleISBN = "9874567231876";
	String wrongFormatISBN = "9874567231833376";
    public static final int LIBRARIAN = 2;
    public static final int CREATEITEM=8;

	@Test
	public void existingTitleTest() {
		Output output = outputHandler.createItem(existingTitleISBN);
		assertEquals("Success!", output.getOutput());
		assertEquals(LIBRARIAN, output.getState());
	}
	
	@Test
	public void newTitleTest() {
		Output output = outputHandler.createItem(newTitleISBN);
		assertEquals("The Title Does Not Exists!", output.getOutput());
		assertEquals(LIBRARIAN, output.getState());
	}
	
	@Test
	public void incorrectTitleFormatTest() {
		Output output = outputHandler.createItem(wrongFormatISBN);
		assertEquals("Your input should in this format:'ISBN',ISBN should be a 13-digit number", output.getOutput());
		assertEquals(CREATEITEM, output.getState());
	}

}
