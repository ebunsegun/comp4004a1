package unit_tests;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import server.logic.model.Loan;
import server.logic.tables.LoanTable;

public class AddLoanTest {

	@Test
	public void test() {
		int loanTableSize = LoanTable.getInstance().getLoanTable().size();
		Loan loan = new Loan(0, "9000987964566", "1", new Date(),"0");
		LoanTable.getInstance().getLoanTable().add(loan);
		assertEquals(loanTableSize + 1, LoanTable.getInstance().getLoanTable().size());
		assertEquals("9000987964566", LoanTable.getInstance().getLoanTable().get(loanTableSize).getIsbn());
		assertEquals("1", LoanTable.getInstance().getLoanTable().get(loanTableSize).getCopynumber());
		assertEquals("0", LoanTable.getInstance().getLoanTable().get(loanTableSize).getRenewstate());
	}
}
