package unit_tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import server.logic.model.User;
import server.logic.tables.UserTable;

public class AddUserTest {

	@Test
	public void test() {
		int userTableSize = UserTable.getInstance().getUserTable().size();
		User user = new User(userTableSize, "Kizito@carleton.ca", "Kizito" );
		UserTable.getInstance().getUserTable().add(user);
		assertEquals(userTableSize + 1, UserTable.getInstance().getUserTable().size());
		assertEquals("Kizito", UserTable.getInstance().getUserTable().get(userTableSize).getPassword());
		assertEquals(userTableSize, UserTable.getInstance().getUserTable().get(userTableSize).getUserid());
		assertEquals("Kizito@carleton.ca", UserTable.getInstance().getUserTable().get(userTableSize).getUsername());
	}
}
