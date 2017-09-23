package com.yapp.midi.controller;

import java.nio.file.Watchable;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RollDiceController {
	/* 
	 * http request : http://localhost:8080/midi/rollDice?id=1
	 * our midifile name format is consist of DiceNumbers
	 * ex pinkDice = 1, pupleDice = 2, midifileName is "12.mid"
	 * */
	@RequestMapping(value="/rollDice", method=RequestMethod.GET)
	@ResponseBody
	public String getRollTest(@RequestParam(value = "sequence")int seq ,HttpServletRequest httpServletRequest){
		JSONArray jarr = new JSONArray();
		JSONObject jobj = new JSONObject();
		int rightDice = getDiceResult();
		int leftDice = getDiceResult();
		jobj.put("pupleDice", leftDice);
		jobj.put("midiPath", rightDice+String.valueOf(leftDice)+".midi");
		jobj.put("pattern", rightDice+String.valueOf(leftDice)+".png");
		jobj.put("pinkDice", rightDice);
		jobj.put("sequence", seq+1);
		jarr.add(jobj);
		return jarr.toString();
	}
	public int getDiceResult(){
		Random randomNum = new Random();
		int DicetNum = randomNum.nextInt(6)+1;
		return DicetNum;
	}
	
}
