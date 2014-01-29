package headfirst.combining.djmvc;

/*
 * mvc pattern
 * implementation of controller
 */

public class BeatController implements ControllerInterface {

	BeatModelInterface model;
	DJView view;
	
	public BeatController(BeatModelInterface model) {
		// pass model in constructor and create the view
		this.model = model;
		view = new DJView(this, model);
		view.createView();
		view.createControls();
		view.disableStopMenuItem();
		view.enableStartMenuItem();
		model.initialize();
	}

	@Override
	public void start() {
		// start model and alter user interface
		model.on();
		view.disableStartMenuItem();
		view.enableStopMenuItem();
	}

	@Override
	public void stop() {
		// stop model and alter user interface
		model.off();
		view.disableStopMenuItem();
		view.enableStartMenuItem();
	}

	@Override
	public void increaseBPM() {
		// get actual bpm and increase it
		int bpm = model.getBPM();
		model.setBPM(bpm + 1);

	}

	@Override
	public void decreaseBPM() {
		// get actual bpm and decrease it
		int bpm = model.getBPM();
		model.setBPM(bpm - 1);
	}

	@Override
	public void setBPM(int bpm) {
		// if user set bpm, controller sets bpm
		model.setBPM(bpm);
	}

}
