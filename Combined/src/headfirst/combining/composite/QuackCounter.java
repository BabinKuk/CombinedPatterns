package headfirst.combining.composite;

/*
 * quack counter is decorator that gives other classes
 * some new behavior (counting) by wrapping them in decorator object 
 */
public class QuackCounter implements Quackable {
	
	//the instance we're trying to decorate
	Quackable duck;
	
	//counter for all quacks
	static int numberOfQuacks;
	
	public QuackCounter(Quackable duck) {
		// TODO Auto-generated constructor stub
		// duck/goose we're trying to decorate
		this.duck = duck;
	}

	@Override
	public void quack() {
		// TODO Auto-generated method stub
		duck.quack();
		
		//increase counter
		numberOfQuacks++;
	}
	
	//return number of quacks
	public static int getQuacks() {
		return numberOfQuacks;
	}

}
