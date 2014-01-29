package headfirst.combining.djmvcsimple;

/*
 * mvc pattern
 * implementation of controller
 */

public class BeatController implements ControllerInterface {
	
	//references to model and view
	BeatModelInterface model;
	DJView view;
	
	public BeatController(BeatModelInterface model) {
		//pass model in constructor and create the view
		System.out.println("in controller constructor");
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
		//start model and alter user interface
		model.on();
		view.disableStartMenuItem();
		view.enableStopMenuItem();
	}

	@Override
	public void stop() {
		//stop model and alter user interface
		model.off();
		view.disableStopMenuItem();
		view.enableStartMenuItem();
	}

	@Override
	public void increaseBPM() {
		//get actual bpm and increase it
		int bpm = model.getBPM();
		System.out.println("model.getBPM() " + model.getBPM());
		model.setBPM(bpm + 1);
	}

	@Override
	public void decreaseBPM() {
		//get actual bpm and decrease it
		int bpm = model.getBPM();
		System.out.println("model.getBPM() " + model.getBPM());
		model.setBPM(bpm - 1);
	}

	@Override
	public void setBPM(int bpm) {
		//if user set bpm, controller sets bpm
		System.out.println("BeatController.setBPM()");
		model.setBPM(bpm);
	}

}