package unit_tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import server.logic.tables.FeeTable;

public class RemoveFeeTest {

	@Test
	public void test() {
		int feeTableSize = FeeTable.getInstance().getFeeTable().size();
		FeeTable.getInstance().getFeeTable().remove(feeTableSize -1);
		assertEquals(feeTableSize - 1, FeeTable.getInstance().getFeeTable().size());
	}
}
