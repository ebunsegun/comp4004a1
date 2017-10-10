package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;

public class RenewLoanTest {
	OutputHandler outputHandler = new OutputHandler();
    public static final int USER = 3;
    public static final int RENEW = 15;
    String user = "Sun@carleton.ca,9781442668584,1";
    String userWithBorrow = "Yu@carleton.ca,9781442668584,1";
    String userBorrowInfoWrongFormat = "Zhibo@carleton.ca,9781442668584";
    
    @Test
    public void renewWithoutBorrowTest() {
    	Output output = outputHandler.renew(user);
    	assertEquals("The loan does not exist!", output.getOutput());
    	assertEquals(USER, output.getState()); 
    }
    
    @Test
    public void renewAfterBorrowTest() {
    	Output output = outputHandler.renew(userWithBorrow);
    	assertEquals("Success!", output.getOutput());
    	assertEquals(USER, output.getState()); 
    }
    
    @Test
    public void borrowWrongFormatTest() {
    	Output output = outputHandler.renew(userBorrowInfoWrongFormat);
    	assertEquals("Your input should in this format:'useremail,ISBN,copynumber'", output.getOutput());
    	assertEquals(RENEW, output.getState()); 
    }

}
