package tfl;

import java.awt.Label;

public abstract class Akter extends Thread {
	
	private Label forDisplay;
	private boolean running;
	
	public Akter(Label forDisplay) {
		this.forDisplay = forDisplay;
		start();
	}
	
	protected abstract void workload();
	
	public void show() {
		forDisplay.setText(toString());
	}
	
	public synchronized void pause() {
		running=false;
	}
	
	public synchronized void go() {
		running=true;
		notify();
	}
	
	public void abort() {
		interrupt();
	}

	@Override
	public void run() {
		try {
			while(!interrupted()) {
				synchronized (this) {
					if(!running) wait();
				}
				if(forDisplay!=null) show();
				workload();
				if(forDisplay!=null) show();
			}
		}
		catch(InterruptedException i) {}
	}
	
	

}
