package com.mididice.util;

import java.util.UUID;

public class GenerateFileName {

	public static String createName(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
}
