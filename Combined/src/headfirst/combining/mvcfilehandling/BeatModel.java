package headfirst.combining.mvcfilehandling;

import java.util.ArrayList;
import javax.sound.midi.*;

/*
 * mvc pattern 
 */

public class BeatModel implements IBeatModelInterface {
	
	//observer
	ArrayList bpmObservers = new ArrayList();
	
	//default inst var
	int bpm = 90;
	
	public BeatModel() {
		// TODO Auto-generated constructor stub
		System.out.println("in model constructor");
	}

	@Override
	public void initialize() {
		//set up sequencer and beat tracks
		System.out.println("initializing model...");
	}

	@Override
	public void on() {
		//start sequencer and set default bpm
		System.out.println("start sequencer and set default bpm");
		setBPM(90);
	}

	@Override
	public void off() {
		//shut down sequencer and set bpm to 0
		System.out.println("shutting down...");
		setBPM(0);
	}

	@Override
	public void setBPM(int bpm) {
		//set bpm
		this.bpm = bpm;
		System.out.println("BeatModel.setBPM() " + bpm);
		//notify bpm observers
		notifyBPMObservers();
	}

	@Override
	public int getBPM() {
		//get bpm inst var
		return bpm;
	}

	public void notifyBPMObservers() {
		System.out.println("BeatModel.notifyBPMObservers()");
		//notify observers that the model state changed
		for(int i = 0; i < bpmObservers.size(); i++) {
			BPMObserver observer = (BPMObserver)bpmObservers.get(i);
			System.out.println("observer " + observer);
			observer.updateBPM();
		}
	}

	@Override
	public void registerObserver(BPMObserver o) {
		System.out.println("BeatModel.registerObserver()");
		bpmObservers.add(o);
		System.out.println("bpmObservers " + bpmObservers);
	}

	@Override
	public void removeObserver(BPMObserver o) {
		System.out.println("BeatModel.removeObserver()");
		
	}

}