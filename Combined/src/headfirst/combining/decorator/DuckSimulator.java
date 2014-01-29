package headfirst.combining.decorator;

/*
 * adapter pattern
 * goose class is added that must simulate like ducks
 * goose adapter is added
 * 
 * decorator pattern added
 * new class quack counter is added with new behavior for other classes
 * previous duck classes are not changed 
 */
public class DuckSimulator {

	public DuckSimulator() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DuckSimulator simulator = new DuckSimulator();
		simulator.simulate();
	}
	
	public void simulate() {
				
		//create ducks of quackable interface
		//each duck is wrapped in a new deocrator (quack counter)
		Quackable mallard = new QuackCounter(new MallardDuck());
		Quackable redhead = new QuackCounter(new RedHeadDuck());
		Quackable rubber = new QuackCounter(new RubberDuck());
		Quackable duckCall = new QuackCounter(new DuckCall());
		//the goose is wrapped around goose adapter
		//we dont count geese honks, so it's not decorated 
		Quackable goose = new GooseAdapter(new Goose());

		System.out.println("Let's simulate...");
		
		simulate(mallard);
		simulate(redhead);
		simulate(rubber);
		simulate(duckCall);	
		simulate(goose);
		
		System.out.println("Geese quacked " + QuackCounter.getQuacks() + " times.");
	}
	
	//overload the simulate method (different signature)
	public void simulate (Quackable duck) {
		duck.quack();
	}

}
