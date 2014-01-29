package headfirst.combining.heartbeat;

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
		IBeatModelInterface model = new BeatModel();
		IControllerInterface controller = new BeatController(model);
	}

}
