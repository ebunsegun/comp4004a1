package unit_tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;

public class ReturnLoanTest {
	OutputHandler outputHandler = new OutputHandler();
    public static final int USER = 3;
    public static final int RETURN = 16;
    String user = "Sun@carleton.ca,9781442668584,1";
    String userWithBorrow = "Yu@carleton.ca,8781611687910,1";
    String userReturnInfoWrongFormat = "Zhibo@carleton.ca,9781442668584";
    
    @Test
    public void returnWithoutBorrowTest() {
    	Output output = outputHandler.returnBook(user);
    	assertEquals("The Loan Does Not Exist!", output.getOutput());
    	assertEquals(USER, output.getState()); 
    }
    
    @Test
    public void returnAfterBorrowTest() {
    	Output output = outputHandler.returnBook(userWithBorrow);
    	assertEquals("Success!", output.getOutput());
    	assertEquals(USER, output.getState()); 
    }
    
    @Test
    public void returnWrongFormatTest() {
    	Output output = outputHandler.returnBook(userReturnInfoWrongFormat);
    	assertEquals("Your input should in this format:'useremail,ISBN,copynumber'", output.getOutput());
    	assertEquals(RETURN, output.getState()); 
    }

}
