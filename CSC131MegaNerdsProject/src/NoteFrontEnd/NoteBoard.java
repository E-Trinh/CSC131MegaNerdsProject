package NoteFrontEnd;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import NoteBackEnd.*;

public class NoteBoard extends JPanel{
	
	private NotesList data;
	
	public NoteBoard(NotesList data) {
		super();
		this.data = data;
		setupLayout();
		this.setVisible(true);
	}
	
	public void setupLayout() {
		GridLayout grid = new GridLayout();
		grid.setColumns(1);
		grid.setRows(data.size());
		this.setLayout(grid);
		ArrayList<Button> noteBtn = new ArrayList<Button>();
		for (int i = 0; i < data.size(); i++) {
			noteBtn.add(new Button(data.get(i).getTitle()));
			this.add(noteBtn.get(i));
		}
	}
}
