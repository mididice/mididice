package com.yapp.midi;

import java.io.File;
import java.io.IOException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

import com.yapp.midi.util.Encrypt;
 
public class Midifile2 {
	public static void main(String[] args) throws Exception{
		
		SecretKey key = KeyGenerator.getInstance("DES").generateKey();
	    Encrypt encrypter = new Encrypt(key);
	    String encrypted = encrypter.encrypt("filename");
	    String decrypted = encrypter.decrypt(encrypted);
	    
	    System.out.println(encrypted);
	    System.out.println(decrypted);
	
	}    
}


	
	/*
	 public static final int NOTE_ON = 0x90;
	    public static final int NOTE_OFF = 0x80;
	    public static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};

	    public static void main(String[] args) throws Exception {
	        Sequence sequence = MidiSystem.getSequence(new File("C:/project/Midi/midi/testmidi2.mid"));

	        int trackNumber = 0;
	        for (Track track :  sequence.getTracks()) {
	            trackNumber++;
	            System.out.println("Track " + trackNumber + ": size = " + track.size());
	            System.out.println();
	            for (int i=0; i < track.size(); i++) { 
	                MidiEvent event = track.get(i);
	                System.out.print("@" + event.getTick() + " ");
	                MidiMessage message = event.getMessage();
	                if (message instanceof ShortMessage) {
	                    ShortMessage sm = (ShortMessage) message;
	                    System.out.print("Channel: " + sm.getChannel() + " ");
	                    if (sm.getCommand() == NOTE_ON) {
	                        int key = sm.getData1();
	                        int octave = (key / 12)-1;
	                        int note = key % 12;
	                        String noteName = NOTE_NAMES[note];
	                        int velocity = sm.getData2();
	                        System.out.println("Note on, " + noteName + octave + " key=" + key + " velocity: " + velocity);
	                    } else if (sm.getCommand() == NOTE_OFF) {
	                        int key = sm.getData1();
	                        int octave = (key / 12)-1;
	                        int note = key % 12;
	                        String noteName = NOTE_NAMES[note];
	                        int velocity = sm.getData2();
	                        System.out.println("Note off, " + noteName + octave + " key=" + key + " velocity: " + velocity);
	                    } else {
	                        System.out.println("Command:" + sm.getCommand());
	                    }
	                } else {
	                    System.out.println("Other message: " + message.getClass());
	                }
	            }

	            System.out.println();
	        }

	    }
}
*/