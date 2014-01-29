package headfirst.combining.factory;

/*
 * abstract factory class
 * creation of ducks is encapsulated
 */

public abstract class AbstractDuckFactory {

	public AbstractDuckFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public abstract Quackable createMallardDuck();
	public abstract Quackable createRedHeadDuck();
	public abstract Quackable createRubberDuck();
	public abstract Quackable createDuckCall();

}
