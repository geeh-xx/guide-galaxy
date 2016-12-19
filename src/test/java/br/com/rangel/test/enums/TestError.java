package br.com.rangel.test.enums;

import junit.framework.Assert;

import org.junit.Test;

import br.com.rangel.app.ErrorMessage;
import br.com.rangel.enums.ErrorCodes;

public class TestError {

	@Test
	public void errorCode(){
		ErrorMessage em = new ErrorMessage();
		
		Assert.assertEquals("No input was specified ! Program exited",em.getMessage(ErrorCodes.NO_INPUT));
		Assert.assertEquals("Input format is wrong ! input discarded",em.getMessage(ErrorCodes.INVALID));
		Assert.assertEquals("Illegal character specified in roman number!",em.getMessage(ErrorCodes.INVALID_ROMAN_CHARACTER));
		Assert.assertEquals("Voilated roman number format",em.getMessage(ErrorCodes.INVALID_ROMAN_STRING));
		Assert.assertEquals("Exception caused during processing due to incorrect line type supplied",em.getMessage(ErrorCodes.INCORRECT_LINE_TYPE));
		Assert.assertEquals("I have no idea what you are talking about",em.getMessage(ErrorCodes.NO_IDEA));

	}
	
}
