package headfirst.combining.djmvc;

/*
 * mvc pattern 
 */

public interface BeatModelInterface {
	//these are the methods the controller will use to direct model based on user's actions
	//initialize beat generator
	public void initialize();
	
	//turn beat generator on
	public void on();
	
	//turn beat generator off
	public void off();
	
	//set BPM
	public void setBPM(int bpm);
	
	//these methods allow view and controller to get state from model and become observers
	//there are two kinds of observers: for every beat (beat observer) and for bpm change (bpm observer) 
	//get BPM
	public int getBPM();
	
	public void registerObserver(BeatObserver o);
	
	public void registerObserver(BPMObserver o);
	
	public void removeObserver(BeatObserver o);
	
	public void removeObserver(BPMObserver o);
	
}
