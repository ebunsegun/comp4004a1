package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.tables.TitleTable;

public class CreateTitleTest {
	
	OutputHandler outputHandler = new OutputHandler();
	String existingTitleInfo = "9781442668584,By the grace of God";
	String existingISBN = "9781442668584";
	String newTitleInfo = "9874567231876,New beginnigs";
	String newISBN = "9874567231876";
	String wrongFormat = "New beginnigs,9874567231876";
    public static final int LIBRARIAN = 2;
    public static final int CREATETITLE=7;

	@Test
	public void existingTitleTest() {
		Output output = outputHandler.createTitle(existingTitleInfo);
		assertEquals("The Title Already Exists!", output.getOutput());
		assertEquals(LIBRARIAN, output.getState());
		assertTrue(TitleTable.getInstance().lookup(existingISBN));
	}
	
	@Test
	public void newTitleTest() {
		Output output = outputHandler.createTitle(newTitleInfo);
		assertEquals("Success!", output.getOutput());
		assertEquals(LIBRARIAN, output.getState());
		assertTrue(TitleTable.getInstance().lookup(newISBN));
	}
	
	@Test
	public void incorrectTitleFormatTest() {
		Output output = outputHandler.createTitle(wrongFormat);
		assertEquals("Your input should be in this format:'ISBN,title',ISBN should be a 13-digit number", output.getOutput());
		assertEquals(CREATETITLE, output.getState());
		assertTrue(TitleTable.getInstance().lookup(newISBN));
	}

}
