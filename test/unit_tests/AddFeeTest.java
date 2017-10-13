package unit_tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import server.logic.model.Fee;
import server.logic.tables.FeeTable;

public class AddFeeTest {

	@Test
	public void test() {
		int feeTableSize = FeeTable.getInstance().getFeeTable().size();
		Fee fee = new Fee(0, 10);
		FeeTable.getInstance().getFeeTable().add(fee);
		assertEquals(feeTableSize + 1, FeeTable.getInstance().getFeeTable().size());
		assertEquals(0, FeeTable.getInstance().getFeeTable().get(feeTableSize).getUserid());
		assertEquals(10, FeeTable.getInstance().getFeeTable().get(feeTableSize).getFee());
	}
}
