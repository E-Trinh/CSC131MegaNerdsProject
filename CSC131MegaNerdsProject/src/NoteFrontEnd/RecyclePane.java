package NoteFrontEnd;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import NoteBackEnd.*;

/*
 * RecyclePane extends JPanel
 * GUI Panel for the recycled Note objects
 */

public class RecyclePane extends JPanel{
	
	//contains a reference to the NotesList list
	private NotesList data;
	
	//int used for storing the current page number
	private int pageNumber = 0;
	
	//int used for the number of notes on each page
	private int notePerPage = 10;
	
	//used for storing the two sets of buttons
	private ArrayList<Button> restoreBtn = new ArrayList<Button>();
	private ArrayList<Button> deleteBtn = new ArrayList<Button>();
	
	//takes a reference to NotesList as parameter, constructor
	public RecyclePane(NotesList data) {
		super();
		this.data = data;
		setupLayout();
		this.setVisible(true);
	}
	
	//returns and accepts nothing, used for initial setup of the frame
	public void setupLayout() {
		//initializing and setting up a grid layout manager;
		GridLayout grid = new GridLayout(0, 2);

		//sets frame to use the grid layout manager previously created
		this.setLayout(grid);
		
		//Setting up elements
		Label titleLabel = new Label("Notes");
		titleLabel.setBackground(Color.yellow);
		titleLabel.setAlignment(Label.CENTER);
		this.add(titleLabel);
		
		Button clearNote = new Button("Clear All Notes");
		clearNote.setBackground(Color.green);
		//Adds ActionListener with anonymous method for deleting all notes from the recycle bin
		clearNote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if the user presses yes on the dialog box, takes the data from the box and creates a new Note
				int deleteNoteDialog = JOptionPane.showConfirmDialog(null, "Clear archive? All items will no be recoverable.");
				if (deleteNoteDialog == JOptionPane.YES_OPTION) {
					data.deleteAll();
					refresh();
				} else {
					refresh();
				}
			}
		});
		this.add(clearNote);
		
		//instantiating needed buttons
		for (int i = 0; i < notePerPage; i++) {
			restoreBtn.add(new NoteButton());
			restoreBtn.get(i).setVisible(false);
			//Adds ActionListener with anonymous method for restoring the note that matches with the corresponding button index
			restoreBtn.get(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int restoreResult = JOptionPane.showConfirmDialog(null, "Restore this note?");
					if (restoreResult == JOptionPane.YES_OPTION) {
						int buttonIndex = restoreBtn.indexOf(e.getSource())  + pageNumber * notePerPage;
						data.restoreNote(buttonIndex);
						refresh();
					}
				}
			});
			
			deleteBtn.add(new Button("Recycle"));
			deleteBtn.get(i).setVisible(false);
			//adds ActionListener with anonymous method for recycling the note that matches with the button index
			deleteBtn.get(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int deleteResult = JOptionPane.showConfirmDialog(null, "Delete this note? Note will no longer be recoverable");
					if (deleteResult == JOptionPane.YES_OPTION) {
						int buttonIndex = deleteBtn.indexOf(e.getSource()) + pageNumber * notePerPage;
						data.delete(buttonIndex);
						refresh();
					}
				}
			});
			
			this.add(restoreBtn.get(i));
			this.add(deleteBtn.get(i));
		}
		
		Button forwardPage = new Button("Forward");
		//adds ActionListener with anonymous method for showing the next page of notes
		forwardPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((pageNumber + 1) * notePerPage < data.recycleBinSize()) {
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
	
	//returns and accepts void, used for refreshing all the components in the frame
	public void refresh() {
		pageNumber = 0;
		updateButton();
	}
	
	//returns and accepts void, used for refreshing all the buttons in the panel
	public void updateButton() {
		for (int i = 0; i < notePerPage; i++) {
			if (notePerPage * pageNumber + i < data.recycleBinSize()) {
				restoreBtn.get(i).setLabel(data.recycleBinGet(notePerPage*pageNumber+i).getTitle());
				restoreBtn.get(i).setVisible(true);
				deleteBtn.get(i).setVisible(true);
			} else {
				restoreBtn.get(i).setVisible(false);
				deleteBtn.get(i).setVisible(false);
			}
		}
	}
}
