package tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.tables.ItemTable;

public class RemoveItemTest {
	OutputHandler outputHandler = new OutputHandler();
	String existingTitleISBN = "9781611687910,1";
	String existingTitleLoaned = "9781442668584,1";
	String nonExistentTitleISBN = "9781487667191,1";
	String wrongFormat = "653139823";
    public static final int LIBRARIAN = 2;
    public static final int DELETEITEM=11;
    
	@Test
	public void existingItemTest() {
		Output output = outputHandler.removeItem(existingTitleISBN);
		assertEquals("Success!", output.getOutput());
		assertEquals(LIBRARIAN, output.getState());
	}
	
	@Test
	public void existingItemLoanedOut() {
		Output output = outputHandler.removeItem(existingTitleLoaned);
		assertEquals("Active Loan Exists!", output.getOutput());
		assertEquals(LIBRARIAN, output.getState());
		String [] itemInfo = existingTitleLoaned.split(",");
		assertTrue(ItemTable.getInstance().lookup(itemInfo[0], itemInfo[1]));
	}
	
	@Test
	public void nonExistingItemTest() {
		Output output = outputHandler.removeItem(nonExistentTitleISBN);
		assertEquals("The Item Does Not Exist!", output.getOutput());
		assertEquals(LIBRARIAN, output.getState());
	}
	
	@Test
	public void incorrectFormatTest() {
		Output output = outputHandler.removeItem(wrongFormat);
		assertEquals("Your input should be in this format:'ISBN,copynumber',ISBN should be a 13-digit number", output.getOutput());
		assertEquals(DELETEITEM, output.getState());
	}

}
