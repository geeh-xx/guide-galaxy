package br.com.rangel.app;

import br.com.rangel.enums.Type;

/**
 * Class filters and conversation
 * 
 * @author Rangel Soares
 */
public class ConversationLine {

	public static String patternAssigned = "^([A-Za-z]+) is ([I|V|X|L|C|D|M])$";
	public static String patternCredits = "^([A-Za-z]+)([A-Za-z\\s]*) is ([0-9]+) ([c|C]redits)$";
	public static String patternHowMuch = "^how much is (([A-Za-z\\s])+)\\?$";
	public static String patternHowMany= "^how many [c|C]redits is (([A-Za-z\\s])+)\\?$";
	private LineFilter[] linefilter;

	/**
	 *Initializes the line filters, i.e the four type of lines
	 */
	public ConversationLine(){
		this.linefilter = new LineFilter[4];
		this.linefilter[0] = new LineFilter(Type.ASSIGNED, patternAssigned);
		this.linefilter[1] = new LineFilter(Type.CREDITS, patternCredits);
		this.linefilter[2] = new LineFilter(Type.QUESTION_HOW_MUCH, patternHowMuch);
		this.linefilter[3] = new LineFilter(Type.QUESTION_HOW_MANY, patternHowMany);
		
	}
	
	/**
	 * method returns the line type for the a particular line
	 * @param line String
	 * @return lineType Type
	 */
	public Type getLineType(String line){
		line = line.trim();
		Type result = Type.NOMATCH;
		
		boolean matched = false;
			
		for(int i =0;i<linefilter.length && !matched ;i++){
			if( line.matches(linefilter[i].getPattern()) ){
				matched = true;
				result = linefilter[i].getType();
			}
			
		}
		
		return result;
		
	}
	
}
