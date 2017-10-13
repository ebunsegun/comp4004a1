package scenario_tests;


import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.tables.ItemTable;
import server.logic.tables.LoanTable;
import server.logic.tables.TitleTable;
import server.logic.tables.UserTable;

public class RemoveLoanedItemTest {
	OutputHandler outputHandler = new OutputHandler();
    public static final int LIBRARIAN=2;
    
    String username = "";
    int userId = 0;
    String ISBN = "";
    
    @Before
    public void setUp() throws ParseException {
    	//Create and add a new user to the system
    	username = "Joe@carleton.ca";
    	UserTable.getInstance().createuser(username, "Joe");
    	userId = UserTable.getInstance().lookup(username);
    	ISBN = "9004444000654";
    	
    	TitleTable.getInstance().createtitle(ISBN, "Future Years");
    	ItemTable.getInstance().createitem(ISBN); 
    	LoanTable.getInstance().createloan(userId, ISBN, "1", new Date());

    }
    
	@Test
	public void existingItemLoanTest() {
		int userId = UserTable.getInstance().lookup(username);
		Output output = outputHandler.removeTitle(ISBN);
		assertEquals("Active Loan Exists!", output.getOutput());
		assertEquals(LIBRARIAN, output.getState());
		assertEquals(UserTable.getInstance().lookup(username), userId);
	}

}
