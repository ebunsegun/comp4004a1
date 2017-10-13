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

public class BorrowLoanCopyExceedingLoanLimitest {
	OutputHandler outputHandler = new OutputHandler();
    public static final int USER = 3;
    String newUsername = "";
    String ISBN = "";
    int userId = 0;
    String borrowInformation = "";
    
    @Before
    public void setUp() {
    	//Create and add a new user to the system and set up 3 already existing loans for the user
    	newUsername = "John@carleton.ca";
    	UserTable.getInstance().createuser(newUsername, "John");
    	userId = UserTable.getInstance().lookup(newUsername);
    	
    	TitleTable.getInstance().createtitle("9000000000001", "Book A");
    	ItemTable.getInstance().createitem("9000000000001");   	
    	LoanTable.getInstance().createloan(userId, "9000000000001", "1", new Date());
    	
    	TitleTable.getInstance().createtitle("9000000000002", "Book B");
    	ItemTable.getInstance().createitem("9000000000002");
    	LoanTable.getInstance().createloan(userId, "9000000000002", "1", new Date());
    	
    	TitleTable.getInstance().createtitle("9000000000003", "Book C");
    	ItemTable.getInstance().createitem("9000000000003");
    	LoanTable.getInstance().createloan(userId, "9000000000003", "1", new Date());
    	
    	
    	//Add a new title and item to the system for this user to borrow
    	ISBN = "9123456789135";
    	TitleTable.getInstance().createtitle(ISBN, "Beliefs of the System");
    	ItemTable.getInstance().createitem(ISBN);
    	borrowInformation = newUsername + "," + ISBN + ",1";
    }

    @Test
    public void borrowWithExistingFeeTest() {
    	Output output = outputHandler.borrow(borrowInformation);
    	assertEquals("The Maximun Number of Items is Reached!", output.getOutput());
    	assertEquals(USER, output.getState()); 

    }

}
