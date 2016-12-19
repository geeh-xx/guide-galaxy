package br.com.rangel.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

import br.com.rangel.enums.ErrorCodes;
import br.com.rangel.util.Util;
import br.com.rangel.enums.Type;


/**
 * Class process  credit,questions and answer
 * 
 * @author Rangel Soares
 */
public class Talk {
	
	public Talk(){
		this.scan = new Scanner(System.in);
		this.conversationLine = new ConversationLine();
		this.eMessage = new ErrorMessage();
		this.constantAssignments = new HashMap<String, String>();
		this.computedLiterals = new  HashMap<String, String>();
		this.output = new ArrayList<String>();
	}
	

	private Scanner scan;
	private ConversationLine conversationLine;
	private ErrorMessage eMessage;
	/**
	 * This is the hash map that will contain the value for each identifier
	 */
	private HashMap<String, String> constantAssignments;
	
	/**
	 * This is the hash map for storing the value of the calculated literal
	 */
	private HashMap<String, String> computedLiterals;
	
	/**
	 * This variable of ArrayList type that will contain the answers for the questions asked in input from console.<br>
	 */
	private ArrayList<String> output;
	
		
	/**
	 * This method reads the paragraph from the input console.
	 * The input sequence can be terminated by a blank new line.<br>
	 * @return output ArrayList
	 */
	public ArrayList<String> read(){
		
		String line;
		int count=0;
		ErrorCodes error = null;
		
		while(this.scan.hasNextLine() && (line = this.scan.nextLine()).length()>0 ){
			
			error = validate(line);
			
			switch(error){
				case NO_IDEA :  this.output.add(this.eMessage.getMessage(error));break;
				
				default : this.eMessage.printMessage(error);
			}
			
			count++;
		}
		
		switch(count){
			case 0: error = ErrorCodes.NO_INPUT;
					this.eMessage.printMessage(error);
					break;
					
			default : 
		}
		
		return this.output;
		
	}
	
	/**
	 *This method first determines the type of line
	 *Based on the type of line it process each input line
	 * @param line
	 * @return error Errorcodes
	 */
	private ErrorCodes validate(String line){
		
		ErrorCodes error = ErrorCodes.SUCCESS_OK;
		
		Type lineType = this.conversationLine.getLineType(line);
		
		switch(lineType){
			case ASSIGNED : 		 processAssignmentLine(line);
							         break;
							
			case CREDITS :			 processCreditsLine(line);
						    		 break;
						    
			case QUESTION_HOW_MUCH : processHowMuchQuestion(line);
									 break;
									 
			case QUESTION_HOW_MANY : processHowManyCreditsQuestion(line);
									 break;
			
			default : error = ErrorCodes.NO_IDEA; break;
		}
				
		return error;
	}
	
	
	
	
	
	/**
	 * This method process the assignment line
	 * It extracts the constant roman literal from input string and adds it constantAssignments hash map
	 * @param line
	 * @throws ArrayIndexOutOfBoundsException
	 */
	private void processAssignmentLine(String line)
	{
		String[] splited = line.trim().split("\\s+");
		
		try{
			constantAssignments.put(splited[0], splited[2]);
		}
		catch(ArrayIndexOutOfBoundsException e){
			this.eMessage.printMessage(ErrorCodes.INCORRECT_LINE_TYPE);
			Util.println(e.getMessage());
		}
	}
	
	
	
	
	
	
	/**
	 * This method process the line for type of question
	 * @param line
	 */
	private void processHowMuchQuestion(String line)
	{
		try{
			
			String formatted = line.split("\\sis\\s")[1].trim();
			
			formatted = formatted.replace("?","").trim();
			
			String keys[] = formatted.split("\\s+");
			
			String romanResult="";
			String completeResult = null;
			boolean errorOccured = false;
			
			for(String key : keys){
				String romanValue = constantAssignments.get(key);
				if(romanValue==null){
					completeResult = this.eMessage.getMessage(ErrorCodes.NO_IDEA);
					errorOccured = true;
					break;
				}
				romanResult += romanValue;
			}
			
			if(!errorOccured){
				romanResult = RomanNumbers.romanToArabic(romanResult);
				completeResult = formatted+" is "+romanResult;
			}
				
			output.add(completeResult);
			
		}
		catch(Exception e){
			this.eMessage.printMessage(ErrorCodes.INCORRECT_LINE_TYPE);
			Util.println(e.getMessage());
			
		}
	}
	
	
	/**
	 * This method process the line for credit computation for line type CREDITS defined in ConversationLine type
	 * @param line String
	 */
	
	private void processCreditsLine(String line)
	{
		try{
			
			String formatted = line.replaceAll("(is\\s+)|([c|C]redits\\s*)","").trim();
			
			String[] keys = formatted.split("\\s");
			
			String toBeComputed = keys[keys.length-2];
			float value = Float.parseFloat(keys[keys.length-1]);
			
			String roman="";
			
			for(int i=0;i<keys.length-2;i++){
				roman += constantAssignments.get(keys[i]);
			}
			
			int romanNumber = Integer.parseInt(RomanNumbers.romanToArabic(roman));
			float credit = (float)(value/romanNumber);
			
					
			computedLiterals.put(toBeComputed, credit+"");
		}
		catch(Exception e)
		{
			
			this.eMessage.printMessage(ErrorCodes.INCORRECT_LINE_TYPE);
			Util.println(e.getMessage());
			
		}
	}
	
	
	/**
	 * This will calculate the answer for how many credits question.
	 * @param line
	 */
	private void processHowManyCreditsQuestion(String line) {
		
		try{
			
			String formatted = line.split("(\\sis\\s)")[1];
			
			formatted = formatted.replace("?","").trim();
			
			String[] keys = formatted.split("\\s");
			
			boolean found = false;
			String roman = "";
			String outputResult = null;
			Stack<Float> cvalues = new Stack<Float>();
			
			for(String key : keys){
				
				found = false;
				
				String romanValue = constantAssignments.get(key);
				if(romanValue!=null){
					roman += romanValue;
					found = true;
				}
				
				String computedValue = computedLiterals.get(key);
				if(!found && computedValue!=null){
					cvalues.push(Float.parseFloat(computedValue));
					found = true;
				}
				
				if(!found){
					outputResult = this.eMessage.getMessage(ErrorCodes.NO_IDEA);
					break;
				}
			}
			
			if(found){
				float res=1;
				for(int i =0;i<cvalues.size();i++)
				res *= cvalues.get(i);
					
				int finalres= (int) res;
				if(roman.length()>0)
				finalres = (int)(Integer.parseInt(RomanNumbers.romanToArabic(roman))*res);
				outputResult = formatted +" is "+ finalres +" Credits";
			}
			
			this.output.add(outputResult);
			
		}
		catch(Exception e){
			this.eMessage.printMessage(ErrorCodes.INCORRECT_LINE_TYPE);
			Util.println(e.getMessage());
		}
		
	}
	
}
