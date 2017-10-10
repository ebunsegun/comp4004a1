package server.logic.handler;

import java.util.Date;

import server.logic.handler.model.Output;
import server.logic.tables.FeeTable;
import server.logic.tables.ItemTable;
import server.logic.tables.LoanTable;
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
    public static final int DELETETITLE=10;
    public static final int DELETEITEM=11;
    public static final int MONITORSYSTEM=12;
    public static final int COLLECTFINE=13;
    public static final int BORROWLOANCOPY=14;
    public static final int RENEW=15;
    public static final int RETURN=16;

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
        			+ "Monitor System.");
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
	
	public Output removeTitle(String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        boolean number=isInteger(strArray[0]);
        Object result="";
        if(strArray.length!=1 || number!=true){
        	output.setOutput("Your input should be in this format:'ISBN',ISBN should be a 13-digit number");
        	output.setState(DELETETITLE);
        }else{
        	result=TitleTable.getInstance().delete(strArray[0]);
        	if(result.equals("success")){
        		output.setOutput("Success!");
        	}else{
        		output.setOutput(result+"!");
        	}
        	output.setState(LIBRARIAN);
        }
		return output;
	}
	
	public Output removeItem(String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        boolean number=isInteger(strArray[0]);
        Object result="";
        if(strArray.length!=2 || number!=true){
        	output.setOutput("Your input should be in this format:'ISBN,copynumber',ISBN should be a 13-digit number");
        	output.setState(DELETEITEM);
        }else{
        	boolean copynumber=isNumber(strArray[1]);
        	if(copynumber!=true){
        		output.setOutput("Your input should be in this format:'ISBN,copynumber',ISBN should be a 13-digit number");
            	output.setState(DELETEITEM);
        	}else{
        		result=ItemTable.getInstance().delete(strArray[0], strArray[1]);
            	if(result.equals("success")){
            		output.setOutput("Success!");
            	}else{
            		output.setOutput(result+"!");
            	}
            	output.setState(LIBRARIAN);
        	}
        }
		return output;
	}
	
	public Output monitorSystem() {
		Output output=new Output("",0);
		String bookTitles ="BOOK TITLES\n";
		String users = "USERS\n";
		for (int i=0; i< TitleTable.getInstance().getTitleTable().size(); i++) {
			bookTitles += TitleTable.getInstance().getTitleTable().get(i).getISBN() + " - " +
					TitleTable.getInstance().getTitleTable().get(i).getBooktitle() + "\n"; 
		}
		
		for (int i=0; i< UserTable.getInstance().getUserTable().size(); i++) {
			users += UserTable.getInstance().getUserTable().get(i).getUsername() + "\n"; 
		}
		
		output.setOutput(bookTitles + "\n" + users);
		output.setState(LIBRARIAN);
		return output;
	}
	
	public Output payFine(String input) {
		Output output=new Output("",0);
        boolean email=input.contains("@");
        int userid=UserTable.getInstance().lookup(input);
        Object result="";
        if(email!=true){
        	output.setOutput("Your input should in this format:'useremail'");
        	output.setState(COLLECTFINE);
        }else if(userid==-1){
        	output.setOutput("The User Does Not Exist!");
        	output.setState(COLLECTFINE);
        }else{
        	result=FeeTable.getInstance().payfine(userid);	
        	if(result.equals("success")){
        		output.setOutput("Success!");
        		}else{
            		output.setOutput(result+"!");
            	}
        		output.setState(USER);
        	}
        	
		return output;
	}
	
	public Output borrow(String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        boolean email=strArray[0].contains("@");
        int userid=UserTable.getInstance().lookup(strArray[0]);
        Object result="";
        if(strArray.length!=3 || email!=true){
        	output.setOutput("Your input should in this format:'useremail,ISBN,copynumber'");
        	output.setState(BORROWLOANCOPY);
        }else if(userid==-1){
        	output.setOutput("The User Does Not Exist!");
        	output.setState(BORROWLOANCOPY);
        }else{
        	boolean ISBN=isInteger(strArray[1]);
        	boolean copynumber=isNumber(strArray[2]);
        	if(ISBN!=true || copynumber!=true){
        		output.setOutput("Your input should in this format:'useremail,ISBN,copynumber'");
            	output.setState(BORROWLOANCOPY);
        	}else{
        		result=LoanTable.getInstance().createloan(userid, strArray[1], strArray[2], new Date());
        		if(result.equals("success")){
            		output.setOutput("Success!");
            	}else{
            		output.setOutput(result+"!");
            	}
        	}
        	output.setState(USER);
        }
		return output;
	}
	
	public Output renew(String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        boolean email=strArray[0].contains("@");
        int userid=UserTable.getInstance().lookup(strArray[0]);
        Object result="";
        if(strArray.length!=3 || email!=true){
        	output.setOutput("Your input should in this format:'useremail,ISBN,copynumber'");
        	output.setState(RENEW);
        }else if(userid==-1){
        	output.setOutput("The User Does Not Exist!");
        	output.setState(RENEW);
        }else{
        	boolean ISBN=isInteger(strArray[1]);
        	boolean copynumber=isNumber(strArray[2]);
        	if(ISBN!=true || copynumber!=true){
        		output.setOutput("Your input should in this format:'useremail,ISBN,copynumber'");
            	output.setState(RENEW);
        	}else{
        		result=LoanTable.getInstance().renewal(userid, strArray[1], strArray[2], new Date());
        		if(result.equals("success")){
            		output.setOutput("Success!");
            	}else{
            		output.setOutput(result+"!");
            	}
        	}
        	output.setState(USER);
        }
		return output;
	}
	
	public Output returnBook(String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        boolean email=strArray[0].contains("@");
        int userid=UserTable.getInstance().lookup(strArray[0]);
        Object result="";
        if(strArray.length!=3 || email!=true){
        	output.setOutput("Your input should in this format:'useremail,ISBN,copynumber'");
        	output.setState(RETURN);
        }else if(userid==-1){
        	output.setOutput("The User Does Not Exist!");
        	output.setState(RETURN);
        }else{
        	boolean ISBN=isInteger(strArray[1]);
        	boolean copynumber=isNumber(strArray[2]);
        	if(ISBN!=true || copynumber!=true){
        		output.setOutput("Your input should in this format:'useremail,ISBN,copynumber'");
            	output.setState(RETURN);
        	}else{
        		result=LoanTable.getInstance().returnItem(userid, strArray[1], strArray[2], new Date());
        		if(result.equals("success")){
            		output.setOutput("Success!");
            	}else{
            		output.setOutput(result+"!");
            	}
        	}
        	output.setState(USER);
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
