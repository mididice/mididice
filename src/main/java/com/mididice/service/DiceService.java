package com.mididice.service;

import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;


@Service
public class DiceService {

	public String rollTheDice(int seq) {
		JSONArray jarr = new JSONArray();
		JSONObject jobj = new JSONObject();
		int rightDice = getRandomNumber();
		int leftDice = getRandomNumber();
		jobj.put("pupleDice", leftDice);
		jobj.put("midiPath", rightDice+String.valueOf(leftDice)+".mid");
		jobj.put("pattern", rightDice+String.valueOf(leftDice)+".png");
		jobj.put("pinkDice", rightDice);
		jobj.put("sequence", seq+1);
		jarr.add(jobj);
		return jarr.toString();
	}
	
	private int getRandomNumber(){
		Random randomNum = new Random();
		int DicetNum = randomNum.nextInt(6)+1;
		return DicetNum;
	}
}
