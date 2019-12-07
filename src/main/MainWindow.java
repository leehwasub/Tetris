package main;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public MainWindow() {
		setSize(1280, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		JPanel panel = new MainPanel();
		getContentPane().add(panel);
		panel.setFocusable(true);
		panel.setRequestFocusEnabled(true);
		panel.grabFocus();
	}

}
