package com.yapp.midi.controller;

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
	 * */
	@RequestMapping(value="/rollDice", method=RequestMethod.GET)
	@ResponseBody
	public String getRollTest(@RequestParam(value="id")int id){
		JSONArray jarr = new JSONArray();
		JSONObject jobj = new JSONObject();
		jobj.put("test", "roll dice!");
		jarr.add(jobj);
		return jarr.toString();
	}
}
