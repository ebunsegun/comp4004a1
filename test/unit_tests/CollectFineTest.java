package unit_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.model.Fee;
import server.logic.tables.FeeTable;
import server.logic.tables.UserTable;

public class CollectFineTest {
	OutputHandler outputHandler = new OutputHandler();
    public static final int USER = 3;
    public static final int COLLECTFINE = 13;
    String username = "Kevin@carleton.ca";
	int userId = UserTable.getInstance().lookup(username);
	Fee fee = new Fee(userId, 10);
	
	String userNotOwing = "Michelle@carleton.ca";
	int userIdNotOwing = UserTable.getInstance().lookup(userNotOwing);
    
    @Test
    public void checkUserFeeTest() {
    	FeeTable.getInstance().getFeeTable().add(fee);    	
    	assertTrue(FeeTable.getInstance().lookup(userId));
    	assertEquals(fee.getFee(), FeeTable.getInstance().lookupfee(userId));
    }
    
    @Test
    public void collectFineFromUserTest() {
    	FeeTable.getInstance().getFeeTable().add(fee); 
    	Output output = outputHandler.payFine(username);
		assertEquals("Success!", output.getOutput());
		assertEquals(USER, output.getState());    	
    	assertFalse(FeeTable.getInstance().lookup(userId));
    	assertEquals(0, FeeTable.getInstance().lookupfee(userId));
    }
    
    @Test
    public void noOutstandingFeeTest() {
    	Output output = outputHandler.payFine(userNotOwing);
		assertEquals("No Outstanding Fees To Be Payed!", output.getOutput());
		assertEquals(USER, output.getState());    	
    	assertFalse(FeeTable.getInstance().lookup(userIdNotOwing));
    	assertEquals(0, FeeTable.getInstance().lookupfee(userIdNotOwing));
    }

}
