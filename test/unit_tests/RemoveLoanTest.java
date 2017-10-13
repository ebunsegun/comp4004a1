package unit_tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import server.logic.tables.LoanTable;

public class RemoveLoanTest {

	@Test
	public void test() {
		int loanTableSize = LoanTable.getInstance().getLoanTable().size();
		LoanTable.getInstance().getLoanTable().remove(loanTableSize -1);
		assertEquals(loanTableSize - 1, LoanTable.getInstance().getLoanTable().size());
	}
}
