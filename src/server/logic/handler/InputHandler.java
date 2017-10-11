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
    public static final int CREATETITLE=7;
    public static final int CREATEITEM=8;
    public static final int DELETEUSER=9;
    public static final int DELETETITLE=10;
    public static final int DELETEITEM=11;
    public static final int MONITORSYSTEM=12;
    public static final int PAYFINE=13;
    public static final int BORROWLOANCOPY=14;
    public static final int RENEW=15;
    public static final int RETURN=16;
    
    int numLibrarians=0;
    
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
	            	if(numLibrarians < 1) {
	            		output="Please Input The Password:";
		            	state=LIBRARIANLOGIN;
		                oo.setOutput(output);
			            oo.setState(state);
		            	numLibrarians++;
	            	} else {
	            		output="Only one librarian allowed in the system";
	            		state=WAITING;
	            		oo.setOutput(output);
			            oo.setState(state);
	            	}        	
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
	        	} else if (input.equalsIgnoreCase("create title")) {
		            output = "Please Input Item Info:'ISBN'";
		            state=CREATETITLE;
		            oo.setOutput(output);
		            oo.setState(state);
	            } else if (input.equalsIgnoreCase("create item")) {
		            output = "Please Input Item Info:'ISBN'";
		            state=CREATEITEM;
		            oo.setOutput(output);
		            oo.setState(state);
	            } else if (input.equalsIgnoreCase("delete user")) {
	            	output = "Please Input User Info:'useremail'";
	            	state=DELETEUSER;
	            	oo.setOutput(output);
		            oo.setState(state);
	            } else if (input.equalsIgnoreCase("delete title")) {
		            output = "Please Input Title Info:'ISBN'";
		            state=DELETETITLE;
		            oo.setOutput(output);
		            oo.setState(state);
	            } else if (input.equalsIgnoreCase("delete item")) {
	            	output = "Please Input Item Info:'ISBN,copynumber'";
	            	state=DELETEITEM;
	            	oo.setOutput(output);
		            oo.setState(state);
	            } else if (input.equalsIgnoreCase("monitor system")) {
	            	state=MONITORSYSTEM;
		            oo.setState(state);
	            } else if(input.equalsIgnoreCase("log out")){
	            	output = "Successfully Log Out!";
	                state = WAITING;
	                oo.setOutput(output);
		            oo.setState(state);
	            } else if(input.equalsIgnoreCase("main menu")){
	        		output = "Please select from the menu.Menu:Create User/Title/Item,Delete User/Title/Item,"
	            			+ "Monitor System.";
	                state = LIBRARIAN;
	                oo.setOutput(output);
		            oo.setState(state);
	        	} else{
	            	output = "Please select from the menu.Menu:Create User/Title/Item,Delete User/Title/Item,"
	            			+  "Monitor System.";
	                state = LIBRARIAN;
	                oo.setOutput(output);
		            oo.setState(state);
	            }
	        } else if(state==USER){
	        	if (input.equalsIgnoreCase("pay fine")) {
	            	output = "Please Input Item Info:'username'";
	            	state=PAYFINE;
	            	oo.setOutput(output);
		            oo.setState(state);
	            } else if (input.equalsIgnoreCase("borrow")) {
	            	output = "Please Input User Info:'useremail,ISBN,copynumber'";
	            	state=BORROWLOANCOPY;
	            	oo.setOutput(output);
		            oo.setState(state);
	            } else if (input.equalsIgnoreCase("renew")) {
	            	output = "Please Input Title Info:'useremail,ISBN,copynumber'";
	            	state=RENEW;
	            	oo.setOutput(output);
		            oo.setState(state);
	            } else if (input.equalsIgnoreCase("return")) {
		            output = "Please Input Item Info:'useremail,ISBN,copynumber'";
		            state=RETURN;
		            oo.setOutput(output);
		            oo.setState(state);
	            } else if(input.equalsIgnoreCase("log out")){
	            	output = "Successfully Log Out!";
	                state = WAITING;
	                oo.setOutput(output);
		            oo.setState(state);
	            } else if(input.equalsIgnoreCase("main menu")){
	        		output = "What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.";
	                state = USER;
	                oo.setOutput(output);
		            oo.setState(state);
	        	} else{
	            	output = "Please select from the menu.Menu:Borrow,Renew,Return,Pay Fine.";
	                state = USER;
	                oo.setOutput(output);
		            oo.setState(state);
	            }
	        } else if(state==CREATEUSER){
	        	if(input.equalsIgnoreCase("log out")){
	            	output = "Successfully Log Out!";
	                state = WAITING;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else if(input.equalsIgnoreCase("main menu")){
	        		output = "Please select from the menu.Menu:Create User/Title/Item,Delete User/Title/Item,"
	            			+ "Monitor System.";
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
	        } else if(state==CREATETITLE){
	        	if(input.equalsIgnoreCase("log out")){
	            	output = "Successfully Log Out!";
	                state = WAITING;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else if(input.equalsIgnoreCase("main menu")){
	        		output = "Please select from the menu.Menu:Create User/Title/Item,Delete User/Title/Item,"
	            			+ "Monitor System.";
	                state = LIBRARIAN;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else{
	        		o=outputHandler.createTitle(input);
	        		output=o.getOutput();
	        		state=o.getState();
	        		oo.setOutput(output);
		            oo.setState(state);
	        	}
	        } else if(state==CREATEITEM){
	        	if(input.equalsIgnoreCase("log out")){
	            	output = "Successfully Log Out!";
	                state = WAITING;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else if(input.equalsIgnoreCase("main menu")){
	        		output = "Please select from the menu.Menu:Create User/Title/Item,Delete User/Title/Item,"
	            			+ "Monitor System.";
	                state = LIBRARIAN;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else{
	        		o=outputHandler.createItem(input);
	        		output=o.getOutput();
	        		state=o.getState();
	        		oo.setOutput(output);
		            oo.setState(state);
	        	}
	        } else if(state==DELETEUSER){
	        	if(input.equalsIgnoreCase("log out")){
	            	output = "Successfully Log Out!";
	                state = WAITING;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else if(input.equalsIgnoreCase("main menu")){
	        		output = "Please select from the menu.Menu:Create User/Title/Item,Delete User/Title/Item,"
	            			+ "Monitor System.";
	                state = LIBRARIAN;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else{
	        		o=outputHandler.removeUser(input);
	        		output=o.getOutput();
	        		state=o.getState();
	        		oo.setOutput(output);
		            oo.setState(state);
	        	}
	        } else if(state==DELETETITLE){
	        	if(input.equalsIgnoreCase("log out")){
	            	output = "Successfully Log Out!";
	                state = WAITING;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else if(input.equalsIgnoreCase("main menu")){
	        		output = "Please select from the menu.Menu:Create User/Title/Item,Delete User/Title/Item,"
	            			+ "Monitor System.";
	                state = LIBRARIAN;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else{
	        		o=outputHandler.removeTitle(input);
	        		output=o.getOutput();
	        		state=o.getState();
	        		oo.setOutput(output);
		            oo.setState(state);
	        	}
	        } else if(state==DELETEITEM){
	        	if(input.equalsIgnoreCase("log out")){
	            	output = "Successfully Log Out!";
	                state = WAITING;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else if(input.equalsIgnoreCase("main menu")){
	        		output = "Please select from the menu.Menu:Create User/Title/Item,Delete User/Title/Item,"
	            			+ "Monitor System.";
	                state = LIBRARIAN;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else{
	        		o=outputHandler.removeItem(input);
	        		output=o.getOutput();
	        		state=o.getState();
	        		oo.setOutput(output);
		            oo.setState(state);
	        	}
	        } else if(state==MONITORSYSTEM){
	        	o=outputHandler.monitorSystem();
	        	output=o.getOutput();
        		state=o.getState();
        		oo.setOutput(output);
	            oo.setState(state);
	        } else if(state==PAYFINE){
	        	if(input.equalsIgnoreCase("log out")){
	            	output = "Successfully Log Out!";
	                state = WAITING;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else if(input.equalsIgnoreCase("main menu")){
	        		output = "What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.";
	                state = USER;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else{
	        		o=outputHandler.payFine(input);
	        		output=o.getOutput();
	        		state=o.getState();
	        		oo.setOutput(output);
		            oo.setState(state);
	        	}
	        } else if(state==BORROWLOANCOPY){
	        	if(input.equalsIgnoreCase("log out")){
	            	output = "Successfully Log Out!";
	                state = WAITING;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else if(input.equalsIgnoreCase("main menu")){
	        		output = "What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.";
	                state = USER;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else{
	        		o=outputHandler.borrow(input);
	        		output=o.getOutput();
	        		state=o.getState();
	        		oo.setOutput(output);
		            oo.setState(state);
	        	}
	        } else if(state==RENEW){
	        	if(input.equalsIgnoreCase("log out")){
	            	output = "Successfully Log Out!";
	                state = WAITING;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else if(input.equalsIgnoreCase("main menu")){
	        		output = "What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.";
	                state = USER;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else{
	        		o=outputHandler.renew(input);
	        		output=o.getOutput();
	        		state=o.getState();
	        		oo.setOutput(output);
		            oo.setState(state);
	        	}
	        }
	        else if(state==RETURN){
	        	if(input.equalsIgnoreCase("log out")){
	            	output = "Successfully Log Out!";
	                state = WAITING;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else if(input.equalsIgnoreCase("main menu")){
	        		output = "What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.";
	                state = USER;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else{
	        		o=outputHandler.returnBook(input);
	        		output=o.getOutput();
	        		state=o.getState();
	        		oo.setOutput(output);
		            oo.setState(state);
	        	}
	        }
	        return oo;
	}

}
