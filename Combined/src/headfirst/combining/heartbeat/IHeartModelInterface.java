package headfirst.combining.heartbeat;

public interface IHeartModelInterface {
	int getHeartRate();
	void registerObserver(IBPMObserver o);
	void removeObserver(IBPMObserver o);
}
