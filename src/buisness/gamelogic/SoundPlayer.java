package buisness.gamelogic;

import org.jfugue.player.Player;

import java.util.Arrays;
import java.util.List;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiChannel;

/**
 * A little example showing how to play a tune in Java.
 *
 * Inputs are not sanitized or checked, this is just to show how simple it is.
 *
 * @author Peter
 */
public class SoundPlayer {

    private List<String> notes = Arrays.asList("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B");
    private MidiChannel[] channels;
    private int INSTRUMENT = 0; // 0 is a piano, 9 is percussion, other channels are for other instruments
    private int VOLUME = 120; // between 0 et 127

    public void toPlay( String note,int duration) {

        try {
            // * Open a synthesizer
            Synthesizer synth = MidiSystem.getSynthesizer();
            synth.open();
            channels = synth.getChannels();

            // * Play some notes
            play(note,duration);


            // * finish up
            synth.close();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Plays the given note for the given duration
     */
    private void play(String note, int duration) throws InterruptedException
    {
        // * start playing a note
        channels[INSTRUMENT].noteOn(id(note), VOLUME );
        // * wait
        Thread.sleep( duration );
        // * stop playing a note
        channels[INSTRUMENT].noteOff(id(note));
    }

    /**
     * Plays nothing for the given duration
     */
    private void rest(int duration) throws InterruptedException
    {
        Thread.sleep(duration);
    }

    /**
     * Returns the MIDI id for a given note: eg. 4C -> 60
     * @return
     */
    private int id(String note)
    {
        int octave = Integer.parseInt(note.substring(0, 1));
        return notes.indexOf(note.substring(1)) + 12 * octave + 12;
    }
}