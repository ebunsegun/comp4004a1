package scenario_tests;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;

public class RemoveUserTest {
	public static OutputHandler outputHandler = new OutputHandler();
	public static String username = "Eden@carleton.ca";	
	public static String password = "Eden";
	public static String existingUser = username + "," + password;
    public static final int LIBRARIAN = 2;
    
    @Before
    public void setup() {
    	outputHandler.createUser(existingUser);
    }
    
	@Test
	public void existingUserTest() {
		Output output = outputHandler.removeUser(username);
		assertEquals("Success!", output.getOutput());
		assertEquals(LIBRARIAN, output.getState());
	}

}
