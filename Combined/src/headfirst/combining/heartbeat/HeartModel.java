package headfirst.combining.heartbeat;

import java.util.*;

public class HeartModel implements IHeartModelInterface, Runnable {
	
	ArrayList bpmObservers = new ArrayList();
	int time = 1000;
    int bpm = 90;
	Random random = new Random(System.currentTimeMillis());
	Thread thread;

	public HeartModel() {
		System.out.println("HeartModel.HeartModel()");
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public int getHeartRate() {
		System.out.println("HeartModel.getHeartRate()");
		return 60000/time;
	}

	@Override
	public void registerObserver(IBPMObserver o) {
		System.out.println("HeartModel.registerObserver()");
		bpmObservers.add(o);
	}

	@Override
	public void removeObserver(IBPMObserver o) {
		System.out.println("HeartModel.removeObserver()");
		int i = bpmObservers.indexOf(o);
		if (i >= 0) {
			bpmObservers.remove(i);
		}
	}

	@Override
	public void run() {
		System.out.println("HeartModel.run()");
		
	}

}
