package scenario_tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.tables.UserTable;

public class ReturnLoanNonExistentTest {
	OutputHandler outputHandler = new OutputHandler();
    public static final int USER = 3;
    String returnInfo = "";
   
    @Before
    public void setUp() {
    	//Create and add a new user to the system
    	String newUsername = "Jake@carleton.ca";
    	UserTable.getInstance().createuser(newUsername, "Jake");
    	String ISBN = "9000000000007";

    	returnInfo = newUsername + "," + ISBN + ",1";
    }
    
    @Test
    public void returnWithoutBorrowTest() {
    	Output output = outputHandler.returnBook(returnInfo);
    	assertEquals("The Loan Does Not Exist!", output.getOutput());
    	assertEquals(USER, output.getState()); 
    }


}
