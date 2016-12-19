package br.com.rangel.main;

import java.util.ArrayList;

import br.com.rangel.util.Util;


/**
 * Main Class,responsible for running the application
 *
 * @author Rangel Soares
 */
public class Main {

public static void main(String[] args) {
		
		Util.println("GalaxyMerchant! please provide input below in the console");
		
		Talk paragraph = new Talk();
		
		ArrayList<String> output=paragraph.read();
		
		for(int i=0;i<output.size();i++)
		{
			Util.println(output.get(i));
		}
		
		
	}
	
}
