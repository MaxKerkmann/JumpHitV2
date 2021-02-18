package buisness.gamelogic;


import java.util.Arrays;
import java.util.List;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiChannel;
public class SoundPlayer {

    private List<String> notes = Arrays.asList("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B");
    private MidiChannel[] channels;
    private int INSTRUMENT = 0;
    private int VOLUME = 120;

    public void toPlay( String note,int duration) {

        try {
            Synthesizer synth = MidiSystem.getSynthesizer();
            synth.open();
            channels = synth.getChannels();

            channels[INSTRUMENT].noteOn(id(note), VOLUME );
            Thread.sleep( duration );
            channels[INSTRUMENT].noteOff(id(note));

            synth.close();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private int id(String note)
    {
        int octave = Integer.parseInt(note.substring(0, 1));
        return notes.indexOf(note.substring(1)) + 12 * octave + 12;
    }
}