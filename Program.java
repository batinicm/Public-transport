package tfl;

import java.awt.*;
import java.awt.event.*;

public class Program extends Frame {
	
	private Line lin;
	
	public Program() {
		super("Public transport");
		setSize(500, 300);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				lin.abort();
				dispose();
			}
		});
		pop();
		setVisible(true);
	}
	
	private void pop() {
		Panel plo=new Panel(new GridLayout(1, 2));
		add(plo,BorderLayout.NORTH);
		plo.setBackground(Color.LIGHT_GRAY);
		plo.add(new Label("Stops:"));
		plo.add(new Label("Vehicles:"));
		
		plo=new Panel();
		add(plo,BorderLayout.SOUTH);
		plo.setBackground(Color.LIGHT_GRAY);
		Button btn=new Button("Go");
		plo.add(btn);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lin.go();
			}
		});
		
		btn=new Button("Stop");
		plo.add(btn);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lin.pause();
			}
		});
		
		plo=new Panel(new GridLayout(0, 2));
		add(plo,BorderLayout.CENTER);
		
		Panel p1=new Panel(new GridLayout(0, 1));
		plo.add(p1);
		
		Panel p2=new Panel(new GridLayout(0, 1));
		plo.add(p2);
		
		String[] stan= {"Waterloo","Embankment","Charing Cross","Picadilly Circus","Oxford Circus"};
		String[] vozozn= {"139","55"};
		
		lin=new Line(stan, 500, 1000, vozozn, 40, 1000, 2000, 4000, p1, p2);
		
	}
	
	public static void main(String[] args) {
		new Program();
	}

}
