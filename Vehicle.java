package tfl;

import java.awt.Label;

public class Vehicle extends Akter {
	
	private String sign;
	private int cap;
	private int tmin,tmax;
	private int pause;
	private Line lin;
	private Stop curr;
	private int stopNum;
	private int displ=1;
	private int numOfPassengers;
	private boolean driving=true;
	

	public Vehicle(Label forDisplay, String sign, int cap, int tmin, int tmax, int pause, Line lin) {
		super(forDisplay);
		this.sign = sign;
		this.cap = cap;
		this.tmin = tmin;
		this.tmax = tmax;
		this.pause = pause;
		this.lin = lin;
		curr=lin.getStop(stopNum);
	}

	@Override
	protected void workload() {
		try {
		driving=true;
		show();
		sleep((int)(tmin + Math.random()*(tmax-tmin+1)));
		driving=false;
		show();
		sleep(pause);
		if(numOfPassengers!=0) numOfPassengers-=(int)(Math.random()*numOfPassengers);
		int addit=cap-numOfPassengers;
		if(addit>curr.numOfPassengers())
			addit=curr.numOfPassengers();
		numOfPassengers+=addit;
		curr.takeOut(addit);
		if((stopNum+1)>=lin.numOfStops()) displ=-1;
		else 
			if(stopNum<=0) displ=1;
		stopNum+=displ;
		curr=lin.getStop(stopNum);
		}
		catch(InterruptedException i) {super.abort();}
	}

	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(sign);
		if(driving && curr!=null) 
			sb.append(" driving towards ");
		else sb.append(" stopped at ");
		sb.append(curr.stopName() + ": " + numOfPassengers); 
		return sb.toString();
	}
	
	

}
