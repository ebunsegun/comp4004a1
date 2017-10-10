package tests;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;

public class UserLoginTest {
	String existingUser;
	String nonExistentUser;
	String incorrectPassword;
	String wrongFormat;
    public static final int USER = 3;
    public static final int USERLOGIN=5;
    
	@Before
	public void setUp() {
		existingUser = "Yu@carleton.ca,Yu";
		nonExistentUser = "Ebun@carleton.ca,Ebun";
		incorrectPassword = "Yu@carleton.ca,a";
		wrongFormat = "Yu,Yu@carleton.ca";
	}

	@Test
	public void existingUserTest() {
		OutputHandler outputHandler = new OutputHandler();
		Output output = outputHandler.userLogin(existingUser);
		assertEquals("What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.", output.getOutput());
		assertEquals(USER, output.getState());
	}
	
	@Test
	public void nonExistingUserTest() {
		OutputHandler outputHandler = new OutputHandler();
		Output output = outputHandler.userLogin(nonExistentUser);
		assertEquals("The User Does Not Exist!Please The Username and Password:'username,password'", output.getOutput());
		assertEquals(USERLOGIN, output.getState());
	}
	
	@Test
	public void incorrectFormatTest() {
		OutputHandler outputHandler = new OutputHandler();
		Output output = outputHandler.userLogin(wrongFormat);
		assertEquals("Your input should be in this format:'username,password'", output.getOutput());
		assertEquals(USERLOGIN, output.getState());
	}
	
	@Test
	public void wrongPasswordTest() {
		OutputHandler outputHandler = new OutputHandler();
		Output output = outputHandler.userLogin(incorrectPassword);
		assertEquals("Wrong Password!Please Input Username and Password:'username,password'", output.getOutput());
		assertEquals(USERLOGIN, output.getState());
	}

}
