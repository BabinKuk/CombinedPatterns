package headfirst.combining.composite;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * composite pattern added
 * it must implement the same interface as leaf element
 */

public class Flock implements Quackable {
	
	//to hold quackables that belong to flock
	ArrayList quackers = new ArrayList();

	public Flock() {
		// TODO Auto-generated constructor stub
	}
	
	//add quackable to flock
	public void add(Quackable quacker) {
		quackers.add(quacker);
	}

	@Override
	public void quack() {
		// TODO Auto-generated method stub
		//iterate over entire list of Flock and call quack for each element
		//iterator pattern
		Iterator iterator = quackers.iterator();
		while (iterator.hasNext()) {
			Quackable quacker = (Quackable) iterator.next();
			quacker.quack();
		}
	}

}
