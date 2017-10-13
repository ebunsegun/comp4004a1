package scenario_tests;

import static org.junit.Assert.assertEquals;
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

public class ReturnLoanOverdueTest {
	OutputHandler outputHandler = new OutputHandler();
    public static final int USER = 3;
    String returnInfo = "";
    int userId = 0;
    
    @Before
    public void setUp() throws ParseException {
    	//Create and add a new user to the system
    	String newUsername = "Ebun@carleton.ca";
    	UserTable.getInstance().createuser(newUsername, "Ebun");
    	userId = UserTable.getInstance().lookup(newUsername);
    	String ISBN = "9000000000017";
    	
    	TitleTable.getInstance().createtitle(ISBN, "Edge of Glory");
    	ItemTable.getInstance().createitem(ISBN);   	
    	SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
    	String dateInString = "05-10-2017";
    	Date date = sdf.parse(dateInString);
    	LoanTable.getInstance().createloan(userId, ISBN, "1", date);

    	returnInfo = newUsername + "," + ISBN + ",1";
    }

    @Test
    public void returnOverdueTest() {
    	Output output = outputHandler.returnBook(returnInfo);
    	assertEquals("Success!", output.getOutput());
    	assertEquals(USER, output.getState()); 
    	assertTrue(FeeTable.getInstance().lookup(userId));
    }
}
