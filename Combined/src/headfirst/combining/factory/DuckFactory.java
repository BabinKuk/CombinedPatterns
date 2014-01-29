package headfirst.combining.factory;

public class DuckFactory extends AbstractDuckFactory {

	public DuckFactory() {
		// TODO Auto-generated constructor stub
	}

	//each method creates a different kind of quackable
	//simulator doesn't know the type, only that is quackable
	@Override
	public Quackable createMallardDuck() {
		// TODO Auto-generated method stub
		return new MallardDuck();
	}

	@Override
	public Quackable createRedHeadDuck() {
		// TODO Auto-generated method stub
		return new RedHeadDuck();
	}

	@Override
	public Quackable createRubberDuck() {
		// TODO Auto-generated method stub
		return new RubberDuck();
	}

	@Override
	public Quackable createDuckCall() {
		// TODO Auto-generated method stub
		return new DuckCall();
	}

}
