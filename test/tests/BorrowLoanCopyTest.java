package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;

public class BorrowLoanCopyTest {
	OutputHandler outputHandler = new OutputHandler();
    public static final int USER = 3;
    public static final int BORROWLOANCOPY = 14;
    String userWithFee = "Zhibo@carleton.ca,9293949599321,1";
    String userWithoutFee = "Yu@carleton.ca,9781442616899,1";
    String userBorrowInfoWrongFormat = "Zhibo@carleton.ca,9781442668584";
    
    @Test
    public void borrowWithExistingFeeTest() {
    	Output output = outputHandler.borrow(userWithFee);
    	assertEquals("Outstanding Fee Exists!", output.getOutput());
    	assertEquals(USER, output.getState()); 
    }
    
    @Test
    public void borrowWithoutExistingFeeTest() {
    	Output output = outputHandler.borrow(userWithoutFee);
    	assertEquals("Success!", output.getOutput());
    	assertEquals(USER, output.getState()); 
    }
    
    @Test
    public void borrowWrongFormatTest() {
    	Output output = outputHandler.borrow(userBorrowInfoWrongFormat);
    	assertEquals("Your input should in this format:'useremail,ISBN,copynumber'", output.getOutput());
    	assertEquals(BORROWLOANCOPY, output.getState()); 
    }

}
