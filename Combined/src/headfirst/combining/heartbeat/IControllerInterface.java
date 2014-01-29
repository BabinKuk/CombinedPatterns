package headfirst.combining.heartbeat;

/*
 * mvc pattern
 * strategy pattern
 */

public interface IControllerInterface {
	public void start();
	public void stop();
	public void increaseBPM();
	public void decreaseBPM();
	public void setBPM(int bpm);
}