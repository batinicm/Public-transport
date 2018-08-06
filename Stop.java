package tfl;

import java.awt.Label;

public class Stop extends Akter {
	
	private String name;
	private double tmin,tmax;
	private int numOfPassengers;

	public Stop(Label forDisplay, String name, double tmin, double tmax) {
		super(forDisplay);
		this.name = name;
		this.tmin = tmin;
		this.tmax = tmax;
	}


	@Override
	protected void workload() {
		try {
			sleep((int)(tmin + Math.random()*(tmax-tmin+1)));
			numOfPassengers++;
		} catch (InterruptedException e) {super.abort();}

	}

	public synchronized String stopName() {
		return name;
	}


	public synchronized int numOfPassengers() {
		return numOfPassengers;
	}
	
	public synchronized Stop takeOut(int min) {
		numOfPassengers-=min;
		return this;
	}


	@Override
	public String toString() {
		return name + ": " + numOfPassengers;
	}
	
	
	

}
