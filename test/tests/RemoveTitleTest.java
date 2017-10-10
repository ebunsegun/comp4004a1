package tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.tables.FeeTable;
import server.logic.tables.TitleTable;
import server.logic.tables.UserTable;

public class RemoveTitleTest {
	OutputHandler outputHandler = new OutputHandler();
	String existingTitleISBN = "9781442667181";
	String existingTitleLoaned = "9781442668584";
	String nonExistentTitleISBN = "9781487667191";
	String wrongFormat = "653139823";
    public static final int LIBRARIAN = 2;
    public static final int DELETETITLE=10;
    
	@Test
	public void existingTitleTest() {
		Output output = outputHandler.removeTitle(existingTitleISBN);
		assertEquals("Success!", output.getOutput());
		assertEquals(LIBRARIAN, output.getState());
	}
	
	@Test
	public void existingTitleLoanedOut() {
		Output output = outputHandler.removeTitle(existingTitleLoaned);
		assertEquals("Active Loan Exists!", output.getOutput());
		assertEquals(LIBRARIAN, output.getState());
		assertTrue(TitleTable.getInstance().lookup(existingTitleLoaned));
	}
	
	@Test
	public void nonExistingTitleTest() {
		Output output = outputHandler.removeTitle(nonExistentTitleISBN);
		assertEquals("The Title Does Not Exist!", output.getOutput());
		assertEquals(LIBRARIAN, output.getState());
	}
	
	@Test
	public void incorrectFormatTest() {
		Output output = outputHandler.removeTitle(wrongFormat);
		assertEquals("Your input should be in this format:'ISBN',ISBN should be a 13-digit number", output.getOutput());
		assertEquals(DELETETITLE, output.getState());
	}

}
