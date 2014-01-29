package headfirst.combining.heartbeat;

public class HeartTest {

	public HeartTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// create controller and pass it a heartmonitor
		HeartModel heartModel = new HeartModel();
		IControllerInterface model = new HeartController(heartModel);
	}

}
