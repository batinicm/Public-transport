package tfl;

import java.awt.Label;
import java.awt.Panel;

public class Line {
	
	private Stop[] stops;
	private Vehicle[] vehicles;
	
	public Line(String[] stps, double tmin, double tmax, String[] vehs, int cap, int ps, int minVeh,int maxVeh, Panel p1, Panel p2) {
		int i=0;
		
		stops=new Stop[stps.length];
		
		for(String s: stps) {
			Label l=new Label();
			p1.add(l);
			stops[i++]=new Stop(l, s, tmin, tmax);
		}
		
		i=0;
		
		vehicles=new Vehicle[vehs.length];
		
		for(String s: vehs) {
			Label l=new Label();
			p2.add(l);
			vehicles[i++]=new Vehicle(l, s, cap, minVeh, maxVeh, ps, this);
		}
	}
	
	public synchronized int numOfStops() {
		return stops.length;
	}
	
	public synchronized Stop getStop(int x) {
		if(x<0 || x>=stops.length) return null;
		return stops[x];
	}
	
	public synchronized void go() {
		for(Stop s: stops)
			s.go();
		
		for(Vehicle v: vehicles)
			v.go();
	}
	
	public synchronized void pause() {
		for(Stop s: stops)
			s.pause();
		
		for(Vehicle v: vehicles)
			v.pause();
	}
	
	public synchronized void abort() {
		for(Stop s: stops)
			s.abort();
		
		for(Vehicle v: vehicles)
			v.abort();
	}

}
