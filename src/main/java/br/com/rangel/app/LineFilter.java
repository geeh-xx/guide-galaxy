package br.com.rangel.app;

import br.com.rangel.enums.Type;


/**
 * Line filters for Conversation Class
 * 
 * @author Rangel Soares
 */
public class LineFilter {

		private Type type;
		private String pattern;
		
		public LineFilter(Type type,String pattern){
			this.type = type;
			this.pattern = pattern;
		}
		
		public String getPattern(){
			return this.pattern;
					
		}
		
		public void setPattern(String pattern) {
			this.pattern = pattern;
		}
		
		public Type getType(){
			return this.type;
		}

		public void setType(Type type) {
			this.type = type;
		}

	
}
