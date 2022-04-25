package NoteFrontEnd;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import NoteBackEnd.*;

/*
 * NoteBoard extends JPanel
 * Contains the GUI panel for the list of active notes
 */

public class NoteBoard extends JPanel{
	
	//contains a reference to the NotesList list
	private NotesList data;
	
	//int used for storing the current page number
	private int pageNumber = 0;
	
	//int used for the number of notes on each page
	private int notePerPage = 10;
	
	//used for storing the two sets of button
	private ArrayList<NoteButton> noteBtn = new ArrayList<NoteButton>();
	private ArrayList<Button> recycleBtn = new ArrayList<Button>();
	
	//takes a reference to NotesList as parameter, constructor
	public NoteBoard(NotesList data) {
		super();
		this.data = data;
		setupLayout();
		this.setVisible(true);
	}
	
	//returns and accepts nothing, used for initial layout setup of the frame
	public void setupLayout() {
		//initializing and setting up a grid layout manager;
		GridLayout grid = new GridLayout(0, 2);

		//sets frame to use the grid layout manager previously created
		this.setLayout(grid);
		
		//Setting up components
		Label titleLabel = new Label("Notes");
		titleLabel.setBackground(Color.yellow);
		this.add(titleLabel);
		
		Button newNote = new Button("Create New Note");
		newNote.setBackground(Color.green);

		//Adds anonymous method for event handling to create a new note
		newNote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel();
				panel.setLayout(new GridLayout(1,2));
				
				//dialog box is too small for the panel, dialog box needs to be resized
				panel.setSize(1000,1000);
				
				//setting up elements in a frame to pass to dialog box
				JLabel titleLabel = new JLabel("Title:");
				JLabel textLabel = new JLabel("Text:");
				JFormattedTextField titleBox = new JFormattedTextField("");
				JFormattedTextField textBox = new JFormattedTextField("");
				panel.add(titleLabel);
				panel.add(titleBox);
				panel.add(textLabel);
				panel.add(textBox);
				
				//if the user presses yes on the dialog box, takes the data from the box and creates a new Note
				int noteEdit = JOptionPane.showConfirmDialog(null, panel);
				if (noteEdit == JOptionPane.YES_OPTION) {
					data.addNote(titleBox.getText(), titleBox.getText(), null);
					refresh();
				}
			}
		});
		this.add(newNote);
		
		//instantiating needed buttons
		for (int i = 0; i < notePerPage; i++) {
			noteBtn.add(new NoteButton());
			recycleBtn.add(new Button("Recycle"));
			recycleBtn.get(i).setVisible(false);
			//adds ActionListener with anonymous method for recycling the note that matches with the button index
			recycleBtn.get(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int deleteResult = JOptionPane.showConfirmDialog(null, "Recycle this note?");
					if (deleteResult == JOptionPane.YES_OPTION) {
						int buttonIndex = recycleBtn.indexOf(e.getSource()) + pageNumber * notePerPage;
						data.recycleNote(buttonIndex);
						refresh();
					}
				}
			});
			this.add(noteBtn.get(i));
			this.add(recycleBtn.get(i));
		}
		
		Button forwardPage = new Button("Forward");
		//adds ActionListener with anonymous method for showing the next page of notes
		forwardPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((pageNumber + 1) * notePerPage < data.size()) {
					pageNumber++;
					updateButton();
				}
			}
		});
		//adds ActionListener with anonymous method for showing the next page of notes
		Button previousPage = new Button("Back");
		previousPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pageNumber > 0) {
					pageNumber--;
					updateButton();
				}
			}
		});
		this.add(previousPage);
		this.add(forwardPage);
		refresh();
	}
	
	public void refresh() {
		pageNumber = 0;
		updateButton();
	}
	
	//returns and accepts void, used for refreshing all the buttons in the panel
	public void updateButton() {
		for (int i = 0; i < notePerPage; i++) {
			if (notePerPage * pageNumber + i < data.size()) {
				noteBtn.get(i).setNote(data.get(notePerPage*pageNumber+i));
				recycleBtn.get(i).setVisible(true);
			} else {
				noteBtn.get(i).removeHide();
				recycleBtn.get(i).setVisible(false);
			}
		}
	}
}
