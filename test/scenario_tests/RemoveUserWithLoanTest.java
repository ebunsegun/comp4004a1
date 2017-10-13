package scenario_tests;


import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.tables.ItemTable;
import server.logic.tables.LoanTable;
import server.logic.tables.TitleTable;
import server.logic.tables.UserTable;

public class RemoveUserWithLoanTest {
	OutputHandler outputHandler = new OutputHandler();
    public static final int LIBRARIAN = 2;
    public static final int DELETEUSER=9;
    
    String username = "";
    int userId = 0;
    
    @Before
    public void setUp() throws ParseException {
    	//Create and add a new user to the system
    	username = "Drew@carleton.ca";
    	UserTable.getInstance().createuser(username, "Drew");
    	userId = UserTable.getInstance().lookup(username);
    	String ISBN = "9000000000654";
    	
    	TitleTable.getInstance().createtitle(ISBN, "Open Invitation");
    	ItemTable.getInstance().createitem(ISBN);   	
    	SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
    	String dateInString = "15-02-2017";
    	Date date = sdf.parse(dateInString);
    	LoanTable.getInstance().createloan(userId, ISBN, "1", date);

    }
    
	@Test
	public void existingUserWithLoanTest() {
		int userId = UserTable.getInstance().lookup(username);
		Output output = outputHandler.removeUser(username);
		assertEquals("Active Loan Exists!", output.getOutput());
		assertEquals(DELETEUSER, output.getState());
		assertEquals(UserTable.getInstance().lookup(username), userId);
	}

}
