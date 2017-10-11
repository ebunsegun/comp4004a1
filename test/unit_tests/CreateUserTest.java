package unit_tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.tables.UserTable;

public class CreateUserTest {
	OutputHandler outputHandler = new OutputHandler();
	String existingUsername = "Michelle@carleton.ca";	
	String existingUser = "Michelle@carleton.ca,Michelle";
	String newUser = "Carly@carleton.ca,Carly";
	String newUsername = "Carly@carleton.ca";
	String wrongFormat = "Yu,Yu@carleton.ca";
    public static final int LIBRARIAN = 2;
    public static final int CREATEUSER=6;
    
	@Test
	public void existingUserTest() {
		Output output = outputHandler.createUser(existingUser);
		assertEquals("The User Already Exists!", output.getOutput());
		assertEquals(LIBRARIAN, output.getState());
		int userId = UserTable.getInstance().lookup(existingUsername);
		assertTrue(UserTable.getInstance().lookup(userId));
	}
	
	@Test
	public void nonExistingUserTest() {
		Output output = outputHandler.createUser(newUser);
		assertEquals("Success!", output.getOutput());
		assertEquals(LIBRARIAN, output.getState());
		int userId = UserTable.getInstance().lookup(newUsername);
		assertTrue(UserTable.getInstance().lookup(userId));
	}
	
	@Test
	public void incorrectFormatTest() {
		Output output = outputHandler.createUser(wrongFormat);
		assertEquals("Your input should be in this format:'username,password'", output.getOutput());
		assertEquals(CREATEUSER, output.getState());
	}

}
