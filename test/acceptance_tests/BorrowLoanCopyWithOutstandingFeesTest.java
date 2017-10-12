package acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.model.Fee;
import server.logic.tables.FeeTable;
import server.logic.tables.ItemTable;
import server.logic.tables.LoanTable;
import server.logic.tables.TitleTable;
import server.logic.tables.UserTable;

public class BorrowLoanCopyWithOutstandingFeesTest {
	OutputHandler outputHandler = new OutputHandler();
    public static final int USER = 3;
    String newUsername = "";
    String ISBN = "";
    String borrowInformation = "";
    
    @Before
    public void setUp() {
    	//Create and add a new user to the system since new users do not have fees or existing loans
    	newUsername = "Kellis@carleton.ca";
    	UserTable.getInstance().createuser(newUsername, "kellis");
    	//Add a new title and item to the system for this user to borrow
    	ISBN = "9123456789137";
    	TitleTable.getInstance().createtitle(ISBN, "Seasons of Life");
    	ItemTable.getInstance().createitem(ISBN);
    	borrowInformation = newUsername + "," + ISBN + ",1";
    	//Add a fee for user
    	int userId = UserTable.getInstance().lookup(newUsername);
    	Fee fee = new Fee(userId, 10);
    	FeeTable.getInstance().getFeeTable().add(fee);
    }

    @Test
    public void borrowWithExistingFeeTest() {
    	Output output = outputHandler.borrow(borrowInformation);
    	assertEquals("Outstanding Fee Exists!", output.getOutput());
    	assertEquals(USER, output.getState()); 
    	
    	//Pay Fine and Test for success
    	output = outputHandler.payFine(newUsername);
		assertEquals("Success!", output.getOutput());
		assertEquals(USER, output.getState()); 
		
		//Now borrow book and test for success
		output = outputHandler.borrow(borrowInformation);
    	assertEquals("Success!", output.getOutput());
    	assertEquals(USER, output.getState()); 
    	assertTrue(LoanTable.getInstance().lookup(ISBN, "1"));
    }

}
