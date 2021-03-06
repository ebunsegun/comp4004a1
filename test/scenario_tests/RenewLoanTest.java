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

public class RenewLoanTest {
	OutputHandler outputHandler = new OutputHandler();
    public static final int USER = 3;
    String renewalInfo = "";
    
    @Before
    public void setUp() {
    	//Create and add a new user to the system and set up 3 already existing loans for the user
    	String newUsername = "Lexi@carleton.ca";
    	UserTable.getInstance().createuser(newUsername, "Lexi");
    	int userId = UserTable.getInstance().lookup(newUsername);
    	String ISBN = "9000000000044";
    	
    	TitleTable.getInstance().createtitle(ISBN, "Book of Life");
    	ItemTable.getInstance().createitem(ISBN);   	
    	LoanTable.getInstance().createloan(userId, ISBN, "1", new Date());

    	renewalInfo = newUsername + "," + ISBN + ",1";
    }

    @Test
    public void renewAfterBorrowTest() {
    	Output output = outputHandler.renew(renewalInfo);
    	assertEquals("Success!", output.getOutput());
    	assertEquals(USER, output.getState()); 
    }

}
