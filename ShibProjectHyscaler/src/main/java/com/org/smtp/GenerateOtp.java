package com.org.smtp;

import java.util.Random;

public class GenerateOtp {

	public String getOtp(){
		// them to generate our password 
        String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
        String Small_chars = "abcdefghijklmnopqrstuvwxyz"; 
        String numbers = "0123456789"; 
//        String symbols = "!@#$%^&*_=+-/.?<>)";
        String values = Capital_chars + Small_chars + numbers;
        int length = 62; 
        Random random = new Random();
        int size= 6;
        String otp= "";
        for(int i = 0;i<size;i++) {
        	otp+=values.charAt(random.nextInt(length));
        }
		return otp;
	}
}
