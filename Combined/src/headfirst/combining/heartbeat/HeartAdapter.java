package headfirst.combining.heartbeat;

/*
 * mvc pattern
 * adapter class for HeartModel to BeatModel  
 */

public class HeartAdapter implements IBeatModelInterface {

	//reference to heart model
	IHeartModelInterface heart;
	
	public HeartAdapter(IHeartModelInterface heart) {
		System.out.println("HeartAdapter.HeartAdapter()");
		this.heart = heart;
	}

	@Override
	public void initialize() {
		// nothing
	}

	@Override
	public void on() {
		// nothing
	}

	@Override
	public void off() {
		// nothing
	}

	@Override
	public void setBPM(int bpm) {
		// nothing
		System.out.println("HeartAdapter.setBPM()");
	}

	@Override
	public int getBPM() {
		System.out.println("HeartAdapter.getBPM()");
		return heart.getHeartRate();
	}

	@Override
	public void registerObserver(IBPMObserver o) {
		System.out.println("HeartAdapter.registerObserver()");
		heart.registerObserver(o);
	}

	@Override
	public void removeObserver(IBPMObserver o) {
		System.out.println("HeartAdapter.removeObserver()");
		heart.removeObserver(o);
	}

}