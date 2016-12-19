package br.com.rangel.test.enums;

import org.junit.Assert;
import org.junit.Test;

import br.com.rangel.app.RomanNumbers;
import br.com.rangel.enums.Roman;

public class TestRoman {

	@Test
	public void romanValid(){
	  Assert.assertEquals(0, RomanNumbers.validateRomanNumber("T"));
	  Assert.assertEquals(1, RomanNumbers.validateRomanNumber("I"));
	}
	
	@Test
	public void value(){
	  Assert.assertEquals(1,    Roman.I.getValue());
	  Assert.assertEquals(5,    Roman.V.getValue());
	  Assert.assertEquals(10,   Roman.X.getValue());
	  Assert.assertEquals(50,   Roman.L.getValue());
	  Assert.assertEquals(100,  Roman.C.getValue());
	  Assert.assertEquals(500,  Roman.D.getValue());
	  Assert.assertEquals(1000, Roman.M.getValue());
	}
	
}
