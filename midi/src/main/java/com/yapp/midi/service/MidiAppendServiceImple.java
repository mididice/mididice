package com.yapp.midi.service;

import org.springframework.stereotype.Service;

@Service("MidiAppendService")
public class MidiAppendServiceImple implements MidiAppendService {

	@Override
	public void appendMidiTest() {
		// Service Test
		System.out.print("service generated");

	}

}
