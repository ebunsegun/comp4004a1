package scenario_tests;


import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.tables.ItemTable;
import server.logic.tables.TitleTable;
import server.logic.tables.UserTable;

public class RemoveItemTest {
    OutputHandler outputHandler = new OutputHandler();
    public static final int LIBRARIAN = 2;
    String itemInfo="";

    @Before
    public void setUp() throws ParseException {
    	//Create and add a new user to the system
    	String username = "Nick@carleton.ca";
    	UserTable.getInstance().createuser(username, "Nick");
    	String ISBN = "9000033300654";
    	
    	TitleTable.getInstance().createtitle(ISBN, "Flying Mechanisms");
    	ItemTable.getInstance().createitem(ISBN);
    	itemInfo = ISBN + ",1";
    }
    
	@Test
	public void existingItemTest() {
		Output output = outputHandler.removeItem(itemInfo);
		assertEquals("Success!", output.getOutput());
		assertEquals(LIBRARIAN, output.getState());
	}

}
