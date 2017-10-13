package unit_tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import server.logic.tables.TitleTable;

public class RemoveTitleTest {

	@Test
	public void test() {
		int titleTableSize = TitleTable.getInstance().getTitleTable().size();
		TitleTable.getInstance().getTitleTable().remove(titleTableSize -1);
		assertEquals(titleTableSize - 1, TitleTable.getInstance().getTitleTable().size());
	}
}
