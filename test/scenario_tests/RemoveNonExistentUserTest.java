package scenario_tests;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;

public class RemoveNonExistentUserTest {
	OutputHandler outputHandler = new OutputHandler();
	String nonExistentUsername = "Kylie@carleton.ca";
    public static final int DELETEUSER=9;

	@Test
	public void nonExistingUserTest() {
		Output output = outputHandler.removeUser(nonExistentUsername);
		assertEquals("The User Does Not Exist!", output.getOutput());
		assertEquals(DELETEUSER, output.getState());
	}
}
