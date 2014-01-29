package headfirst.combining.heartbeat;

public class HeartController implements IControllerInterface {
	
	IHeartModelInterface model;
	DJView view;

	//pass model to controller and create the view
	public HeartController(IHeartModelInterface model) {
		System.out.println("HeartController.HeartController()");
		this.model = model;
		//pass heart model wrapped in adapter instead of beatmodel
		view = new DJView(this, new HeartAdapter(model));
		view.createView();
		view.createControls();
		view.disableStopMenuItem();
		view.disableStartMenuItem();
	}

	@Override
	public void start() {
		// nothing
	}

	@Override
	public void stop() {
		// nothing
	}

	@Override
	public void increaseBPM() {
		// nothing
	}

	@Override
	public void decreaseBPM() {
		// nothing
	}

	@Override
	public void setBPM(int bpm) {
		// nothing
	}

}
