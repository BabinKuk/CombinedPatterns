package headfirst.combining.decorator;

//adapter must implement target interface (quackable)
public class GooseAdapter implements Quackable {
	
	Goose goose;

	public GooseAdapter(Goose goose) {
		// TODO Auto-generated constructor stub
		//goose we're trying to adapt
		this.goose = goose;
	}

	@Override
	public void quack() {
		// TODO Auto-generated method stub
		goose.honk();
	}

}
