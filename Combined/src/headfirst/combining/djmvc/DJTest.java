package headfirst.combining.djmvc;

public class DJTest {

	public DJTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BeatModelInterface model = new BeatModel();
		ControllerInterface controller = new BeatController(model);
	}

}
