package headfirst.combining.heartbeat;

/*
 * mvc pattern 
 */

public interface IBeatModelInterface {
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
	//get BPM
	public int getBPM();
	
	//observer pattern
	public void registerObserver(IBPMObserver o);
	
	public void removeObserver(IBPMObserver o);

}
