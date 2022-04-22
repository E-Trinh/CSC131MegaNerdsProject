package NoteFrontEnd;

import java.awt.*;
import javax.swing.*;
import NoteBackEnd.*;

public class NoteWindow extends JFrame {
	
	private NotesList data;
	
	public NoteWindow(NotesList data) {
		super();
		this.data = data;
		setupLayout();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setupLayout() {
		this.setSize(1000, 1000);
		Container notePane;
		notePane = getContentPane();
		this.setLayout(new BorderLayout());
		NoteTabPane tabPane = new NoteTabPane(data);
		notePane.add(tabPane);
	}
}
