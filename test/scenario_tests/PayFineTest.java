package scenario_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.tables.FeeTable;
import server.logic.tables.ItemTable;
import server.logic.tables.LoanTable;
import server.logic.tables.TitleTable;
import server.logic.tables.UserTable;

public class PayFineTest {
	OutputHandler outputHandler = new OutputHandler();
    public static final int USER = 3;
    public static final int COLLECTFINE = 13;
    String username = "";
	int userId = 0;
	String returnInfo ="";
	
	@Before
    public void setUp() throws ParseException {
    	//Create and add a new user to the system
		username = "Meena@carleton.ca";
    	UserTable.getInstance().createuser(username, "Meena");
    	userId = UserTable.getInstance().lookup(username);
    	String ISBN = "9000000000036";
    	
    	TitleTable.getInstance().createtitle(ISBN, "Wings");
    	ItemTable.getInstance().createitem(ISBN);   	
    	SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
    	String dateInString = "05-09-2017";
    	Date date = sdf.parse(dateInString);
    	LoanTable.getInstance().createloan(userId, ISBN, "1", date);
    	
    	returnInfo = username + "," + ISBN + ",1";
    
    }
  
    @Test
    public void collectFineFromUserTest() {
    	Output output = outputHandler.returnBook(returnInfo);
    	assertTrue(FeeTable.getInstance().lookup(userId));
    	output = outputHandler.payFine(username);
		assertEquals("Success!", output.getOutput());
		assertEquals(USER, output.getState());    	
    	assertFalse(FeeTable.getInstance().lookup(userId));
    	assertEquals(0, FeeTable.getInstance().lookupfee(userId));
    }
}
