package com.mididice.util;

import java.util.UUID;

public class GenerateFileName {

	public String createName(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
}
