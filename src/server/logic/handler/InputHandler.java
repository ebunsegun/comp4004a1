package server.logic.handler;

import server.logic.handler.model.Output;
import server.logic.handler.model.ServerOutput;

public class InputHandler {
	public static final int WAITING = 0;
	public static final int FINISHWAITING=1;
    public static final int LIBRARIAN = 2;
    public static final int USER = 3;
    public static final int LIBRARIANLOGIN=4;
    public static final int USERLOGIN=5;
    public static final int CREATEUSER=6;
    
    OutputHandler outputHandler=new OutputHandler();

	public ServerOutput processInput(String input, int state) {
		 String output = "";
		 Output o = new Output("",0);
		 ServerOutput oo = new ServerOutput(output,o.getState());
	        if (state == WAITING) {
	        	output = "Who Are you?Librarian or User?";
	            state = FINISHWAITING;
	            oo.setOutput(output);
	            oo.setState(state);
	         }else if (state == FINISHWAITING) {
	            if (input.equalsIgnoreCase("librarian")) {
	            	output="Please Input The Password:";
	            	state=LIBRARIANLOGIN;
	                oo.setOutput(output);
		            oo.setState(state);
	            }else if (input.equalsIgnoreCase("user")) {
	            	output="Please Input Username and Password:'username,password'";
	            	state=USERLOGIN;
	                oo.setOutput(output);
		            oo.setState(state);
	            }else{
	            	output = "Who Are you?Librarian or User?";
	            	state = FINISHWAITING;
	            	oo.setOutput(output);
		            oo.setState(state);
	            }
	        }else if(state==LIBRARIANLOGIN){
	        	o=outputHandler.librarianLogin(input);
        		output=o.getOutput();
        		state=o.getState();
        		oo.setOutput(output);
	            oo.setState(state);
	        }else if(state==USERLOGIN){
	        	o=outputHandler.userLogin(input);
        		output=o.getOutput();
        		state=o.getState();
        		oo.setOutput(output);
	            oo.setState(state);
	        }else if (state==LIBRARIAN){
	        	if (input.equalsIgnoreCase("create user")) {
	            	output = "Please Input User Info:'username,password'";
	            	state=CREATEUSER;
	            	oo.setOutput(output);
		            oo.setState(state);
	        	} else if(input.equalsIgnoreCase("log out")){
	            	output = "Successfully Log Out!";
	                state = WAITING;
	                oo.setOutput(output);
		            oo.setState(state);
	            } else if(input.equalsIgnoreCase("main menu")){
	        		output = "Please select from the menu.Menu:Create User/Title/Item,Delete User/Title/Item,"
	            			+ "Borrow Loancopy, Return LoanCopy, Collect Fine.";
	                state = LIBRARIAN;
	                oo.setOutput(output);
		            oo.setState(state);
	        	} else{
	            	output = "Please select from the menu.Menu:Create User/Title/Item,Delete User/Title/Item,"
	            			+ "Borrow Loancopy, Return LoanCopy, Collect Fine.";
	                state = LIBRARIAN;
	                oo.setOutput(output);
		            oo.setState(state);
	            }
	        }else if (state==USER){ 
	        	
	        }else if(state==CREATEUSER){
	        	if(input.equalsIgnoreCase("log out")){
	            	output = "Successfully Log Out!";
	                state = WAITING;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else if(input.equalsIgnoreCase("main menu")){
	        		output = "Please select from the menu.Menu:Create User/Title/Item,Delete User/Title/Item,"
	            			+ "Borrow Loancopy, Return LoanCopy, Collect Fine.";
	                state = LIBRARIAN;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else{
	        		o=outputHandler.createUser(input);
	        		output=o.getOutput();
	        		state=o.getState();
	        		oo.setOutput(output);
		            oo.setState(state);
	        	}
	        }
	        return oo;
	}

}
