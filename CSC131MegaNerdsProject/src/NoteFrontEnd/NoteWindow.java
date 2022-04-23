package NoteFrontEnd;

import java.awt.*;
import javax.swing.*;
import NoteBackEnd.*;

/*
 * NoteWindow extends JFrame
 * Window containing all components for GUI
 */

public class NoteWindow extends JFrame {
	
	//reference to the NotesList
	private NotesList data;
	
	//constructor, accepts NoteList object and creates new NoteWindow object
	public NoteWindow(NotesList data) {
		super();
		this.data = data;
		setupLayout();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//no parameters and return, sets up the layout of the window
	public void setupLayout() {
		this.setSize(1000, 1000);
		Container notePane;
		notePane = getContentPane();
		this.setLayout(new BorderLayout());
		NoteTabPane tabPane = new NoteTabPane(data);
		notePane.add(tabPane);
	}
}
