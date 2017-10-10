package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.tables.ItemTable;

public class CreateItemTest {
	
	OutputHandler outputHandler = new OutputHandler();
	String existingTitleISBN = ItemTable.getInstance().getItemTable().get(0).getISBN();
	String newTitleISBN = "9874567931876";
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
		assertEquals("The Title Does Not Exist!", output.getOutput());
		assertEquals(LIBRARIAN, output.getState());
	}
	
	@Test
	public void incorrectTitleFormatTest() {
		Output output = outputHandler.createItem(wrongFormatISBN);
		assertEquals("Your input should be in this format:'ISBN',ISBN should be a 13-digit number", output.getOutput());
		assertEquals(CREATEITEM, output.getState());
	}

}
