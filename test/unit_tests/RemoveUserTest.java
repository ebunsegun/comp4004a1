package unit_tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import server.logic.tables.UserTable;

public class RemoveUserTest {

	@Test
	public void addUser() {
		int userTableSize = UserTable.getInstance().getUserTable().size();
		UserTable.getInstance().getUserTable().remove(userTableSize -1);
		assertEquals(userTableSize - 1, UserTable.getInstance().getUserTable().size());
	}
}
