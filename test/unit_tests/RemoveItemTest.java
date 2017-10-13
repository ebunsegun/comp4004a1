package unit_tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import server.logic.tables.ItemTable;

public class RemoveItemTest {

	@Test
	public void test() {
		int itemTableSize = ItemTable.getInstance().getItemTable().size();
		ItemTable.getInstance().getItemTable().remove(itemTableSize -1);
		assertEquals(itemTableSize - 1, ItemTable.getInstance().getItemTable().size());
	}
}
