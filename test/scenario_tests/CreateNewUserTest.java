package scenario_tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.tables.UserTable;

public class CreateNewUserTest {
	public static OutputHandler outputHandler = new OutputHandler();
	public static String newuser = "Fred@carletonca,Fred";
	public static String newUsername = "Fred@carletonca";
    public static final int LIBRARIAN = 2;
    
	@Test
	public void nonExistingUserTest() {
		Output output = outputHandler.createUser(newuser);
		assertEquals("Success!", output.getOutput());
		assertEquals(LIBRARIAN, output.getState());
		int userId = UserTable.getInstance().lookup(newUsername);
		assertTrue(UserTable.getInstance().lookup(userId));
	}
}
