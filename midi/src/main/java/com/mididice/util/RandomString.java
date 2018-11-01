package com.mididice.util;

public class RandomString {
	  static char[] chars = {
		        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
		        'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
		        'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
		        'y', 'z', '0', '1', '2', '3', '4', '5',
		        '6', '7', '8', '9', 'A', 'B', 'C', 'D',
		        'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
		        'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
		        'U', 'V', 'W', 'X', 'Y', 'Z', '_'};

		    // Caesar cipher
		   public String encrypt(String text, int offset)
		    {
		        char[] plain = text.toCharArray();

		        for (int i = 0; i < plain.length; i++) {
		            for (int j = 0; j < chars.length; j++) {
		                if (j <= chars.length - offset) {
		                    if (plain[i] == chars[j]) {
		                        plain[i] = chars[j + offset];
		                        break;
		                    }
		                } 
		                else if (plain[i] == chars[j]) {
		                    plain[i] = chars[j - (chars.length - offset + 1)];
		                }
		            }
		        }
		        return String.valueOf(plain);
		    }

		   public String decrypt(String cip, int offset)
		    {
		        char[] cipher = cip.toCharArray();
		        for (int i = 0; i < cipher.length; i++) {
		            for (int j = 0; j < chars.length; j++) {
		                if (j >= offset && cipher[i] == chars[j]) {
		                    cipher[i] = chars[j - offset];
		                    break;
		                }
		                if (cipher[i] == chars[j] && j < offset) {
		                    cipher[i] = chars[(chars.length - offset +1) + j];
		                    break;
		                }
		            }
		        }
		        return String.valueOf(cipher);
		    }
}
