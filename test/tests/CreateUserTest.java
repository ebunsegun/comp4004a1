package tests;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.tables.UserTable;

public class CreateUserTest {
	String existingUser;
	String existingUsername;
	String nonExistentUser;
	String wrongFormat;
    public static final int LIBRARIAN = 2;
    public static final int CREATEUSER=6;
    
	@Before
	public void setUp() {
		existingUser = "Yu@carleton.ca,Yu";
		existingUsername ="Yu@carleton.ca";
		nonExistentUser = "Ebun@carleton.ca,Ebun";
		wrongFormat = "Yu,Yu@carleton.ca";
	}

	@Test
	public void existingUserTest() {
		OutputHandler outputHandler = new OutputHandler();
		Output output = outputHandler.createUser(existingUser);
		assertEquals("The User Already Exists!", output.getOutput());
		assertEquals(LIBRARIAN, output.getState());
		int userId = UserTable.getInstance().lookup(existingUsername);
		assertTrue(UserTable.getInstance().lookup(userId));
	}
	
	@Test
	public void nonExistingUserTest() {
		OutputHandler outputHandler = new OutputHandler();
		Output output = outputHandler.createUser(nonExistentUser);
		assertEquals("Success!", output.getOutput());
		assertEquals(LIBRARIAN, output.getState());
	}
	
	@Test
	public void incorrectFormatTest() {
		OutputHandler outputHandler = new OutputHandler();
		Output output = outputHandler.createUser(wrongFormat);
		assertEquals("Your input should in this format:'username,password'", output.getOutput());
		assertEquals(CREATEUSER, output.getState());
	}

}
