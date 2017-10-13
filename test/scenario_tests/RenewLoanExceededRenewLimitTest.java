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

public class RenewLoanExceededRenewLimitTest {
	OutputHandler outputHandler = new OutputHandler();
    public static final int USER = 3;
    String renewalInfo = "";
    
    @Before
    public void setUp() throws ParseException {
    	//Create and add a new user to the system and set up 3 already existing loans for the user
    	String newUsername = "Lexa@carleton.ca";
    	UserTable.getInstance().createuser(newUsername, "Lexa");
    	int userId = UserTable.getInstance().lookup(newUsername);
    	String ISBN = "9000000000084";
    	
    	TitleTable.getInstance().createtitle(ISBN, "Book of Lives");
    	ItemTable.getInstance().createitem(ISBN);   	
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
    	String dateInString = "11-10-2017";
    	Date date = sdf.parse(dateInString);
    	LoanTable.getInstance().createloan(userId, ISBN, "1", date);

    	renewalInfo = newUsername + "," + ISBN + ",1";
    	
    	outputHandler.renew(renewalInfo);
    }

    @Test
    public void renewAfterBorrowTest() {
    	Output output = outputHandler.renew(renewalInfo);
    	assertEquals("Renewed Item More Than Once for the Same Loan!", output.getOutput());
    	assertEquals(USER, output.getState()); 
    }

}
