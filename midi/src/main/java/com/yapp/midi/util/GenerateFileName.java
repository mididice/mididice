package com.yapp.midi.util;

import java.util.UUID;

public class GenerateFileName {

	public String createName(){
		UUID uuid = UUID.randomUUID();
		
		return uuid.toString();
	}
	public static void main(String args[]){
		GenerateFileName gfn = new GenerateFileName();
		System.out.println(gfn.createName());
	}
}
