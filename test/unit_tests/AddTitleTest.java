package unit_tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import server.logic.model.Title;
import server.logic.model.User;
import server.logic.tables.TitleTable;
import server.logic.tables.UserTable;

public class AddTitleTest {

	@Test
	public void addUser() {
		int titleTableSize = TitleTable.getInstance().getTitleTable().size();
		Title title = new Title("9009858946732", "No Strings Attached");
		TitleTable.getInstance().getTitleTable().add(title);
		assertEquals(titleTableSize + 1, TitleTable.getInstance().getTitleTable().size());
		assertEquals("9009858946732", TitleTable.getInstance().getTitleTable().get(titleTableSize).getISBN());
		assertEquals("No Strings Attached", TitleTable.getInstance().getTitleTable().get(titleTableSize).getBooktitle());
	}
}
