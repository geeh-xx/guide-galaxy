package br.com.rangel.app;

import br.com.rangel.enums.ErrorCodes;
import br.com.rangel.util.Util;


/**
 * Class filters and conversation
 * 
 * @author Rangel Soares
 */
public class ErrorMessage {

	public ErrorMessage(){
		
	}
	
	/**
	 * message for error code
	 */
	public void printMessage(ErrorCodes error)	{
		String message= getMessage(error);
		
		if(message !=null){
			Util.println(message);
		}
		
	}
	
	/**
	 * 
	 * get message error
	 * 
	 * message for error code
	 * @param error ErrorCodes 
	 * @return String
	 */
	public String getMessage(ErrorCodes error)	{
		String message= null;
		
		switch(error){
		
			case NO_INPUT : message = "No input was specified ! Program exited";
							break;
			case INVALID : message = "Input format is wrong ! input discarded";break;
			
			case INVALID_ROMAN_CHARACTER : message = "Illegal character specified in roman number!";break;
			
			case INVALID_ROMAN_STRING : message =  "Voilated roman number format";break;
			
			case INCORRECT_LINE_TYPE : message =  "Exception caused during processing due to incorrect line type supplied";break;
			
			case NO_IDEA : message = "I have no idea what you are talking about";break;
			
			default : break;
		}
		return message;
	}
	
}
