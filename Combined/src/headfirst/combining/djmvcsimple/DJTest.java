package headfirst.combining.djmvcsimple;

/*
 * mvc pattern
 * the main class
 */

public class DJTest {

	public DJTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// first create model
		// then create controller and pass it the model
		// and then the controller will create the view (not here)
		System.out.println("DJTest.main()");
		BeatModelInterface model = new BeatModel();
		ControllerInterface controller = new BeatController(model);
	}

}
