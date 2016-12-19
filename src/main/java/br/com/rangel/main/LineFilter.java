package br.com.rangel.main;

import br.com.rangel.enums.Type;


/**
 * Line filters for Conversation Class
 * 
 * @author Rangel Soares
 */
public class LineFilter {

		public LineFilter(Type type,String pattern){
			this.type = type;
			this.pattern = pattern;
		}
	
		private Type type;
		private String pattern;
		
		public String getPattern(){
			return this.pattern;
					
		}
		
		public Type getType(){
			return this.type;
		}
	
}
