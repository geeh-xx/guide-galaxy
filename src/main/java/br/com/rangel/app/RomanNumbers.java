package br.com.rangel.app;

import br.com.rangel.enums.ErrorCodes;
import br.com.rangel.enums.Roman;




public class RomanNumbers {

public static final ErrorMessage eMessage = new ErrorMessage();
	
	/**
	 *  Validate roman number entered is valid or not
	 */
	public static String romanNumberValidator = "^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
	
	
	private static  int getValueFromRomanChar(char romanChar){
		int value = -1;
		
		switch(romanChar){
			case 'I' : value = Roman.I.getValue();
						break;
			case 'V' : value = Roman.V.getValue();
						break;
			case 'X' : value = Roman.X.getValue();
						break;
			case 'L' : value = Roman.L.getValue();
						break;
			
			case 'C' : value = Roman.C.getValue();
						break;
			
			case 'D' : value = Roman.D.getValue();
						break;
			
			case 'M' : value = Roman.M.getValue();
						break;
			
		}
		
		return value;
	}
	
	public static String romanToArabic(String roman)
	{
		String result="";
		
		switch(validateRomanNumber(roman))
		{
			case 1 :  result = convert(roman);
					  break;
				
			default : result = RomanNumbers.eMessage.getMessage(ErrorCodes.INVALID_ROMAN_STRING);
		}
		
		return result;
	}
	
	
	/**
	 * <p>This method validates a given roman number<br>
	 * Return 1 when roman number is in correct format or 0 otherwise
	 * </p>
	 * @param roman String
	 * @return boolean
	 */
	public static int validateRomanNumber(String roman)
	{
		int result = 0;
		
		if(roman.matches(romanNumberValidator)){
			result = 1;
		}
		
		return result;
	}
	
	
	/**
	 * Converts roman number to arabic number
	 * @param roman
	 * @return String
	 */
	public static String convert(String roman){
		int decimal = 0;
        int lastNumber = 0;
        
		for(int i=roman.length()-1; i>=0; i--){
			char ch = roman.charAt(i);
			decimal = CheckRoman(getValueFromRomanChar(ch), lastNumber, decimal);
            lastNumber = getValueFromRomanChar(ch);
		}
		
		return decimal+"";
		
	}
	
	
	private static int CheckRoman(int TotalDecimal, int LastRomanLetter, int LastDecimal){
		
        if (LastRomanLetter > TotalDecimal) {
            return LastDecimal - TotalDecimal;
        } else {
            return LastDecimal + TotalDecimal;
        }
        
    }
	
	
}
