package com.yapp.midi;

import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class Midifile3 {
	private static final int   VELOCITY = 64;
	
	 public static void main(String[] args) throws InvalidMidiDataException {
	      File f = new File("C:/project/Midi/midi/testmidi2.mid");
	      File f2 = new File("C:/project/Midi/midi/testmidi.mid");
	      try {
	         Sequence sequence = MidiSystem.getSequence(f2);
	         Sequencer sequencer = MidiSystem.getSequencer();
	         sequencer.setSequence(sequence);
	         
	         
	         Sequence sequence2 = MidiSystem.getSequence(f);
	         
	         Sequencer sequencer2 = MidiSystem.getSequencer();
	         sequencer2.setSequence(sequence2);
	         sequencer2.setSequence(sequence2);
	         sequencer2.open();
	         sequencer2.start();
	         int trackNum2 = 0;
//	         Track editTrack = sequence2.createTrack();
//	         for (Track track : sequence.getTracks()) {
//	         
//	            trackNum2++;
//	            for (int i = 0; i < track2.size(); i++) {
//	               MidiEvent event = track2.get(i);
//	               edittrack2.add(event);
//	            }
//	            
//	         }
	         
	         sequence = new Sequence(Sequence.PPQ, 1);
	         Track track2 = sequence.createTrack();
	         
	         track2.add(createNoteOnEvent(60, 0));
	         track2.add(createNoteOnEvent(64, 0));
	         track2.add(createNoteOnEvent(67, 0));
	         track2.add(createNoteOnEvent(72, 0));
	         track2.add(createNoteOffEvent(60, 1));
	         track2.add(createNoteOffEvent(64, 1));
	         track2.add(createNoteOffEvent(67, 1));
	         track2.add(createNoteOffEvent(72, 1));
	         
	         // second chord: f minor N
	         track2.add(createNoteOnEvent(53, 1));
	         track2.add(createNoteOnEvent(65, 1));
	         track2.add(createNoteOnEvent(68, 1));
	         track2.add(createNoteOnEvent(73, 1));
	         track2.add(createNoteOffEvent(63, 2));
	         track2.add(createNoteOffEvent(65, 2));
	         track2.add(createNoteOffEvent(68, 2));
	         track2.add(createNoteOffEvent(73, 2));
	         
	         // third chord: C major 6-4
	         track2.add(createNoteOnEvent(55, 2));
	         track2.add(createNoteOnEvent(64, 2));
	         track2.add(createNoteOnEvent(67, 2));
	         track2.add(createNoteOnEvent(72, 2));
	         track2.add(createNoteOffEvent(64, 3));
	         track2.add(createNoteOffEvent(72, 3));

	         // forth chord: G major 7
	         track2.add(createNoteOnEvent(65, 3));
	         track2.add(createNoteOnEvent(71, 3));
	         track2.add(createNoteOffEvent(55, 4));
	         track2.add(createNoteOffEvent(65, 4));
	         track2.add(createNoteOffEvent(67, 4));
	         track2.add(createNoteOffEvent(71, 4));
	         
	         // fifth chord: C major
	         track2.add(createNoteOnEvent(48, 4));
	         track2.add(createNoteOnEvent(64, 4));
	         track2.add(createNoteOnEvent(67, 4));
	         track2.add(createNoteOnEvent(72, 4));
	         track2.add(createNoteOffEvent(48, 8));
	         track2.add(createNoteOffEvent(64, 8));
	         track2.add(createNoteOffEvent(67, 8));
	         track2.add(createNoteOffEvent(72, 8));
	         int trackNum = 0;
	         for (Track track : sequence.getTracks()) {
	            trackNum++;
	            for (int i = 0; i < track2.size(); i++) {
	            
	            }
	         }
	         
	         sequencer.setSequence(sequence);
	         sequencer.open();
	         sequencer.start();
	         
	         sequencer.setSequence(sequence2);
	         
	         //sequencer2.open();
	         //sequencer2.start();
	         //sequencer.stop();
	         sequencer.close();
	         
	         System.out.println("trackNum:"+trackNum);
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (MidiUnavailableException e) {
	         e.printStackTrace();
	      }
	   }
	   private static MidiEvent createNoteOnEvent(int nKey, long lTick)
	   {
	      return createNoteEvent(ShortMessage.NOTE_ON,
	                        nKey,
	                        VELOCITY,
	                        lTick);
	   }



	   private static MidiEvent createNoteOffEvent(int nKey, long lTick)
	   {
	      return createNoteEvent(ShortMessage.NOTE_OFF,
	                        nKey,
	                        0,
	                        lTick);
	   }



	   private static MidiEvent createNoteEvent(int nCommand,
	                                  int nKey,
	                                  int nVelocity,
	                                  long lTick)
	   {
	      ShortMessage   message = new ShortMessage();
	      try
	      {
	         message.setMessage(nCommand,
	                        0,   // always on channel 1
	                        nKey,
	                        nVelocity);
	      }
	      catch (InvalidMidiDataException e)
	      {
	         e.printStackTrace();
	         System.exit(1);
	      }
	      MidiEvent   event = new MidiEvent(message,
	                                lTick);
	      return event;
	   }
}
