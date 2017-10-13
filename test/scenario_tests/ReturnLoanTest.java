package scenario_tests;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.tables.ItemTable;
import server.logic.tables.LoanTable;
import server.logic.tables.TitleTable;
import server.logic.tables.UserTable;

public class ReturnLoanTest {
	OutputHandler outputHandler = new OutputHandler();
    public static final int USER = 3;
    String returnInfo = "";
    
    @Before
    public void setUp() {
    	//Create and add a new user to the system
    	String newUsername = "Jude@carleton.ca";
    	UserTable.getInstance().createuser(newUsername, "Jude");
    	int userId = UserTable.getInstance().lookup(newUsername);
    	String ISBN = "9000000000007";
    	
    	TitleTable.getInstance().createtitle(ISBN, "Book of Truth");
    	ItemTable.getInstance().createitem(ISBN);   	
    	LoanTable.getInstance().createloan(userId, ISBN, "1", new Date());

    	returnInfo = newUsername + "," + ISBN + ",1";
    }

    @Test
    public void returnAfterBorrowTest() {
    	Output output = outputHandler.returnBook(returnInfo);
    	assertEquals("Success!", output.getOutput());
    	assertEquals(USER, output.getState()); 
    }
}
