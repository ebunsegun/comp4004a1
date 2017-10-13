package scenario_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.tables.ItemTable;
import server.logic.tables.LoanTable;
import server.logic.tables.TitleTable;
import server.logic.tables.UserTable;

public class BorrowAvailableLoanCopyTest {
	OutputHandler outputHandler = new OutputHandler();
    public static final int USER = 3;
    String newUsername = "";
    String ISBN = "";
    String borrowInformation = "";
    
    @Before
    public void setUp() {
    	//Create and add a new user to the system since new users do not have fees or existing loans
    	newUsername = "Umar@carleton.ca";
    	UserTable.getInstance().createuser(newUsername, "Umar");
    	//Add a new title and item to the system for this user to borrow
    	ISBN = "9123456789125";
    	TitleTable.getInstance().createtitle(ISBN, "Seasons of Life");
    	ItemTable.getInstance().createitem(ISBN);
    	borrowInformation = newUsername + "," + ISBN + ",1";
    }

    @Test
    public void borrowWithoutExistingFeeTest() {
    	Output output = outputHandler.borrow(borrowInformation);
    	assertEquals("Success!", output.getOutput());
    	assertEquals(USER, output.getState()); 
    	assertTrue(LoanTable.getInstance().lookup(ISBN, "1"));
    }

}
