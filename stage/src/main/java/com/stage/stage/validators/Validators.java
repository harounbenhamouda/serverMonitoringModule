package com.stage.stage.validators;

import com.jayway.jsonpath.internal.function.text.Length;
import com.stage.stage.entity.Core_Accounts;

import lombok.Data;
@Data 
public class Validators {
	Core_Accounts cor;
	
	public static boolean isNumeric(String string) {
	    int intValue;
	   
	    System.out.println(String.format("Parsing string: \"%s\"", string));
			
	    if(string == null || string.equals("")) {
	        System.out.println("String cannot be parsed, it is null or empty.");
	        return false;
	    }
	    
	    try {
	        intValue = Integer.parseInt(string);
	        return true;
	    } catch (NumberFormatException e) {
	        System.out.println("Input String cannot be parsed to Integer.");
	    }
	    return false;
	}
	
	
	
	
	public static boolean MenichInt(String s) {
	    try {
	        Integer.parseInt(s);
	        return true;
	    } catch (NumberFormatException ex) {
	    	
	    	return false;
	    	
	    }
	    
	}
	public static boolean MenichString(int s) {
	    try {
	         String test =Integer.toString(s);
	        return true;
	    } catch (NumberFormatException ex) {
	    	
	    	return false;
	    	
	    }
	    
	}
	
	
	
	
}
