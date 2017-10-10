package server.logic.handler;

import server.logic.handler.model.Output;
import server.logic.tables.ItemTable;
import server.logic.tables.TitleTable;
import server.logic.tables.UserTable;
import utilities.Config;

public class OutputHandler {
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

	public Output createUser(String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        boolean email=strArray[0].contains("@");
        Object result="";
        if(strArray.length!=2 || email!=true){
        	output.setOutput("Your input should be in this format:'username,password'");
        	output.setState(CREATEUSER);
        }else{
        	result=UserTable.getInstance().createuser(strArray[0], strArray[1]);
        	if(result.equals(true)){
        		output.setOutput("Success!");
        	}else{
        		output.setOutput("The User Already Exists!");
        	}
        	output.setState(LIBRARIAN);
        }
		return output;
	}

	public Output librarianLogin(String input) {
		Output output=new Output("",0);
		if(input.equalsIgnoreCase(Config.LIBRARIAN_PASSWORD)){
			output.setOutput("Please select from the menu.Menu:Create User/Title/Item,Delete User/Title/Item,"
        			+ "Borrow Loancopy, Return LoanCopy, Collect Fine.");
        	output.setState(LIBRARIAN);
		}else{
			output.setOutput("Wrong Password!Please Input The Password:");
        	output.setState(LIBRARIANLOGIN);
		}
		return output;
	}

	public Output userLogin(String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        boolean email=strArray[0].contains("@");
        int result=0;
        if(strArray.length!=2 || email!=true){
        	output.setOutput("Your input should be in this format:'username,password'");
        	output.setState(USERLOGIN);
        }else{
        	result=UserTable.getInstance().checkUser(strArray[0], strArray[1]);
        	if(result==0){
        		output.setOutput("What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.");
            	output.setState(USER);
        	}else if(result==1){
        		output.setOutput("Wrong Password!Please Input Username and Password:'username,password'");
            	output.setState(USERLOGIN);
        	}else{
        		output.setOutput("The User Does Not Exist!Please The Username and Password:'username,password'");
            	output.setState(USERLOGIN);
        	}
        }
		return output;
	}
	
	public Output createTitle(String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        boolean number=isInteger(strArray[0]);
        Object result="";
        if(strArray.length!=2 || number!=true){
        	output.setOutput("Your input should be in this format:'ISBN,title',ISBN should be a 13-digit number");
        	output.setState(CREATETITLE);
        }else{
        	result=TitleTable.getInstance().createtitle(strArray[0], strArray[1]);
        	if(result.equals(true)){
        		output.setOutput("Success!");
        	}else{
        		output.setOutput("The Title Already Exists!");
        	}
        	output.setState(LIBRARIAN);
        }
		return output;
	}
	
	public Output createItem(String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        boolean number=isInteger(strArray[0]);
        Object result="";
        if(strArray.length!=1 || number!=true){
        	output.setOutput("Your input should be in this format:'ISBN',ISBN should be a 13-digit number");
        	output.setState(CREATEITEM);
        }else{
        	result=ItemTable.getInstance().createitem(strArray[0]);
        	if(result.equals(true)){
        		output.setOutput("Success!");
        	}else{
        		output.setOutput("The Title Does Not Exist!");
        	}
        	output.setState(LIBRARIAN);
        }
		return output;
	}
	
	public Output removeUser(String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        int userid=UserTable.getInstance().lookup(strArray[0]);
        boolean email=strArray[0].contains("@");
        Object result="";
        if(strArray.length!=1 || email!=true){
        	output.setOutput("Your input should be in this format:'useremail'");
        	output.setState(DELETEUSER);
        }else if(userid==-1){
        	output.setOutput("The User Does Not Exist!");
        	output.setState(DELETEUSER);
        }else{
        	result=UserTable.getInstance().delete(userid);
        	if(result.equals("success")){
        		output.setOutput("Success!");
        	}else{
        		output.setOutput(result+"!");
        	}
        	output.setState(LIBRARIAN);
        }
		return output;
	}
	
	public static boolean isInteger(String value) {
		char[] ch = value.toCharArray();
		boolean isNumber=true;
		if(value.length()==13){
			for (int i = 0; i < ch.length; i++) {
				isNumber = Character.isDigit(ch[i]);
			}
		}else{
			isNumber=false;
		}
		return isNumber;
		 }
	
	public boolean isNumber(String value) {
		char[] ch = value.toCharArray();
		boolean isNumber=true;
			for (int i = 0; i < ch.length; i++) {
				isNumber = Character.isDigit(ch[i]);
			}
		return isNumber;
	}
}
