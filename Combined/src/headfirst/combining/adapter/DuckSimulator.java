package headfirst.combining.adapter;

/*
 * adapter pattern
 * goose class is added that must simulate like ducks
 * goose adapter is added
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
		Quackable mallard = new MallardDuck();
		Quackable redhead = new RedHeadDuck();
		Quackable rubber = new RubberDuck();
		Quackable duckCall = new DuckCall();
		//the goose is wrapped around goose adapter
		Quackable goose = new GooseAdapter(new Goose());

		System.out.println("Let's simulate...");
		
		simulate(mallard);
		simulate(redhead);
		simulate(rubber);
		simulate(duckCall);	
		simulate(goose);
	}
	
	//overload the simulate method (different signature)
	public void simulate (Quackable duck) {
		duck.quack();
	}

}
