package headfirst.combining.factory;

/*
 * adapter pattern
 * goose class is added that must simulate like ducks
 * goose adapter is added
 * 
 * decorator pattern added
 * new class quack counter is added with new behavior for other classes
 * 
 * (abstract) factory added - duck creation is encapsulated 
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
		
		//create factory and pass it to simulator
		AbstractDuckFactory duckFactory = new CountingDuckFactory();
		simulator.simulate(duckFactory);
	}
	
	//take abstractduckfactory and use it to create ducks
	//rather than instantiate them directly 
	public void simulate(AbstractDuckFactory duckFactory) {
				
		//create ducks of quackable interface
		//each duck is wrapped in a new decorator (quack counter)
		Quackable mallard = duckFactory.createMallardDuck();
		Quackable redhead = duckFactory.createRedHeadDuck();
		Quackable rubber = duckFactory.createRubberDuck();
		Quackable duckCall = duckFactory.createDuckCall();
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
