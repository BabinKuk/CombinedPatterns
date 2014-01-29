package headfirst.combining.djmvcsimple;

/*
 * mvc pattern
 * strategy pattern
 */

public interface ControllerInterface {
	public void start();
	public void stop();
	public void increaseBPM();
	public void decreaseBPM();
	public void setBPM(int bpm);
}