package headfirst.combining.factory;

public class CountingDuckFactory extends AbstractDuckFactory {

	public CountingDuckFactory() {
		// TODO Auto-generated constructor stub
	}

	//each method wraps quackable with counting decorator
	//simulator will never know the difference, it just gets quackable
	@Override
	public Quackable createMallardDuck() {
		// TODO Auto-generated method stub
		return new QuackCounter(new MallardDuck());
	}

	@Override
	public Quackable createRedHeadDuck() {
		// TODO Auto-generated method stub
		return new QuackCounter(new RedHeadDuck());
	}

	@Override
	public Quackable createRubberDuck() {
		// TODO Auto-generated method stub
		return new QuackCounter(new RubberDuck());
	}

	@Override
	public Quackable createDuckCall() {
		// TODO Auto-generated method stub
		return new QuackCounter(new DuckCall());
	}

}
