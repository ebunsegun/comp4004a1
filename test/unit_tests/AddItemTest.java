package unit_tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import server.logic.model.Item;
import server.logic.tables.ItemTable;

public class AddItemTest {

	@Test
	public void test() {
		int itemTableSize = ItemTable.getInstance().getItemTable().size();
		Item item = new Item(itemTableSize, "9009858946734", "1");
		ItemTable.getInstance().getItemTable().add(item);
		assertEquals(itemTableSize + 1, ItemTable.getInstance().getItemTable().size());
		assertEquals("9009858946734", ItemTable.getInstance().getItemTable().get(itemTableSize).getISBN());
		assertEquals("1", ItemTable.getInstance().getItemTable().get(itemTableSize).getCopynumber());
	}
}
