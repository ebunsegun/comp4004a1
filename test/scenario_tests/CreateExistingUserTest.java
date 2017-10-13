package scenario_tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.tables.UserTable;

public class CreateExistingUserTest {
	public static OutputHandler outputHandler = new OutputHandler();
	public static String username = "Alex@carleton.ca";	
	public static String password = "Alex";
	public static String existingUser = username + "," + password;
    public static final int LIBRARIAN = 2;
    
    @Before
    public void setup() {
    	outputHandler.createUser(existingUser);
    }
    
	@Test
	public void existingUserTest() {
		Output output = outputHandler.createUser(existingUser);
		assertEquals("The User Already Exists!", output.getOutput());
		assertEquals(LIBRARIAN, output.getState());
		int userId = UserTable.getInstance().lookup(username);
		assertTrue(UserTable.getInstance().lookup(userId));
	}
}
