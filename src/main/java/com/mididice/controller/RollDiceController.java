package com.mididice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mididice.service.DiceService;

@RestController
public class RollDiceController {
	
	private DiceService diceService;
	
	public RollDiceController(DiceService diceService) {
		this.diceService = diceService;
	}
	/* 
	 * http request : http://localhost:8080/rolldice?sequence=1
	 * our midifile name format is consist of DiceNumbers
	 * ex pinkDice = 1, pupleDice = 2, midifileName is "12.mid"
	 * */
	@GetMapping("/rolldice")
	public String getRollTest( @RequestParam(value="sequence")int seq){
		return diceService.rollTheDice(seq);
	}
	
}
