package unit_tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.tables.TitleTable;
import server.logic.tables.UserTable;

public class MonitorSystemTest {
	OutputHandler outputHandler = new OutputHandler();
    public static final int LIBRARIAN = 2;
    
    
    @Test
    public void monitorSystem() {
    	Output output = outputHandler.monitorSystem();
    	String bookTitles ="BOOK TITLES\n";
		String users = "USERS\n";
		for (int i=0; i< TitleTable.getInstance().getTitleTable().size(); i++) {
			bookTitles += TitleTable.getInstance().getTitleTable().get(i).getISBN() + " - " +
					TitleTable.getInstance().getTitleTable().get(i).getBooktitle() + "\n"; 
		}
		
		for (int i=0; i< UserTable.getInstance().getUserTable().size(); i++) {
			users += UserTable.getInstance().getUserTable().get(i).getUsername() + "\n"; 
		}
		String expectedMonitorOutput = bookTitles + "\n" + users;
		assertEquals(expectedMonitorOutput, output.getOutput());
		assertEquals(LIBRARIAN,output.getState());
    }

}
