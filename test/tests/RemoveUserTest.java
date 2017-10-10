package tests;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.tables.UserTable;

public class RemoveUserTest {
	OutputHandler outputHandler = new OutputHandler();
	String existingUsername ="Yu@carleton.ca";
	String nonExistentUsername = "Ebun@carleton.ca";
	String wrongFormat = "Yucarleton.ca";
    public static final int LIBRARIAN = 2;
    public static final int DELETEUSER=9;
    
	@Test
	public void existingUserTest() {
		int userId = UserTable.getInstance().lookup(existingUsername);
		Output output = outputHandler.removeUser(existingUsername);
		assertEquals("Success!", output.getOutput());
		assertEquals(LIBRARIAN, output.getState());
		//The user's Id should remain even when the user is deleted since the id cannot be reassigned
		assertTrue(UserTable.getInstance().lookup(userId));
	}
	
	@Test
	public void nonExistingUserTest() {
		Output output = outputHandler.removeUser(nonExistentUsername);
		assertEquals("The User Does Not Exist!", output.getOutput());
		assertEquals(DELETEUSER, output.getState());
	}
	
	@Test
	public void incorrectFormatTest() {
		Output output = outputHandler.removeUser(wrongFormat);
		assertEquals("Your input should be in this format:'useremail'", output.getOutput());
		assertEquals(DELETEUSER, output.getState());
	}

}
