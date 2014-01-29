package headfirst.combining.djmvc;

import java.util.ArrayList;

import javax.sound.midi.*;

/*
 * mvc pattern 
 */

public class BeatModel implements BeatModelInterface, MetaEventListener {
	
	//sequencer object that knows how to generate real beats
	Sequencer sequencer;
	
	//two kinds of observers
	ArrayList beatObservers = new ArrayList();
	ArrayList bpmObservers = new ArrayList();
	
	//deafault inst var
	int bpm = 90;
	
	Sequence sequence;
    Track track;
 	
	public BeatModel() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize() {
		// set up sequencer and beat tracks
		setUpMidi();
		buildTrackAndStart();
	}

	@Override
	public void on() {
		// start sequencer and set default bpm
		sequencer.start();
		setBPM(90);
	}

	@Override
	public void off() {
		// shut down sequencer and set bpm to 0
		setBPM(0);
		sequencer.stop();
	}

	@Override
	public void setBPM(int bpm) {
		// set bpm
		this.bpm = bpm;
		// ask sequencer to change bpm
		sequencer.setTempoInBPM(getBPM());
		// notify bpm observers
		notifyBPMObservers();
	}

	@Override
	public int getBPM() {
		// get bpm inst var
		return bpm;
	}

	@Override
	public void registerObserver(BeatObserver o) {
		// TODO Auto-generated method stub
		beatObservers.add(o);
	}

	@Override
	public void registerObserver(BPMObserver o) {
		// TODO Auto-generated method stub
		bpmObservers.add(o);		
	}

	@Override
	public void removeObserver(BeatObserver o) {
		// TODO Auto-generated method stub
		int i = beatObservers.indexOf(o);
		if (i >= 0) {
			beatObservers.remove(i);
		}
	}

	@Override
	public void removeObserver(BPMObserver o) {
		// TODO Auto-generated method stub
		int i = bpmObservers.indexOf(o);
		if (i >= 0) {
			bpmObservers.remove(i);
		}
	}
	
	public void setUpMidi() {
		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequencer.addMetaEventListener(this);
			sequence = new Sequence(Sequence.PPQ,4);
			track = sequence.createTrack();
			sequencer.setTempoInBPM(getBPM());
		} catch(Exception e) {
				e.printStackTrace();
		}
	}
	
	public void buildTrackAndStart() {
        int[] trackList = {35, 0, 46, 0};
    
        sequence.deleteTrack(null);
        track = sequence.createTrack();

      	makeTracks(trackList);
		track.add(makeEvent(192,9,1,0,4));      
	 	try {
			sequencer.setSequence(sequence);                    
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
	
	public void makeTracks(int[] list) {        
	       
		for (int i = 0; i < list.length; i++) {
			int key = list[i];

			if (key != 0) {
				track.add(makeEvent(144,9,key, 100, i));
				track.add(makeEvent(128,9,key, 100, i+1));
			}
		}
    }
	        
    public  MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
        MidiEvent event = null;
        try {
            ShortMessage a = new ShortMessage();
            a.setMessage(comd, chan, one, two);
            event = new MidiEvent(a, tick);
            
        } catch(Exception e) {
			e.printStackTrace(); 
		}
        return event;
    }

	@Override
	public void meta(MetaMessage message) {
        if (message.getType() == 47) {
			beatEvent();
        	sequencer.start();
        	setBPM(getBPM());
        }
    }
	
	void beatEvent() {
		notifyBeatObservers();
	}
	
	public void notifyBeatObservers() {
		for(int i = 0; i < beatObservers.size(); i++) {
			BeatObserver observer = (BeatObserver)beatObservers.get(i);
			observer.updateBeat();
		}
	}
	
	public void notifyBPMObservers() {
		for(int i = 0; i < bpmObservers.size(); i++) {
			BPMObserver observer = (BPMObserver)bpmObservers.get(i);
			observer.updateBPM();
		}
	}

}
