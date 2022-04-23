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
		Button newNote = new Button("Clear All Notes");
		newNote.setBackground(Color.green);
		
		//Adds ActionListener with anonymous method for deleting all notes from the recycle bin
		newNote.addActionListener(new ActionListener() {
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
		
		this.add(titleLabel);
		this.add(newNote);
		refresh();
	}
	
	//returns and accepts void, used for refreshing all the components in the frame
	public void refresh() {
		if (data.recycleBinSize() > restoreBtn.size()) {
			for (int i = 0; i < data.recycleBinSize(); i++) {
				if (i < restoreBtn.size()) {
					restoreBtn.get(i).setLabel(data.recycleBinGet(i).getTitle());
				} else {
					//Adding additional buttons for recycled notes that do not have buttons
					restoreBtn.add(new Button(data.recycleBinGet(i).getTitle()));
					this.add(restoreBtn.get(i));
					
					//Adds ActionListener with anonymous method for restoring the note that matches with the corresponding button index
					restoreBtn.get(i).addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int restoreResult = JOptionPane.showConfirmDialog(null, "Restore this note?");
							if (restoreResult == JOptionPane.YES_OPTION) {
								int buttonIndex = restoreBtn.indexOf(e.getSource());
								data.restoreNote(buttonIndex);
								refresh();
							}
						}
					});
					deleteBtn.add(new Button("Delete"));
					
					//Adds ActionListener with anonymous method for deleting the note that matches with the corresponding button index
					deleteBtn.get(i).addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int deleteResult = JOptionPane.showConfirmDialog(null, "Delete this note? Note will no longer be recoverable");
							if (deleteResult == JOptionPane.YES_OPTION) {
								int buttonIndex = deleteBtn.indexOf(e.getSource());
								data.delete(buttonIndex);
								refresh();
							}
						}
					});
					this.add(deleteBtn.get(i));
				}
			} 
		} else {
		//when there are more pair of buttons than notes, sets the title to the corresponding buttons and removes the extra buttons
			ArrayList<Integer> nums = new ArrayList<Integer>();
			for (int i = 0; i < restoreBtn.size(); i++) {
				if (i < data.recycleBinSize()) {
					restoreBtn.get(i).setLabel(data.recycleBinGet(i).getTitle());
				} else {
					//adds the index of items that need to removed, the items are removed in reverse order
					nums.add(i);
				}
			}
			//removing the buttons from the frame and lists
			for (int i = nums.size() - 1; i >= 0; i--) {
				int num = nums.get(i);
				this.remove(restoreBtn.get(num));
				this.remove(deleteBtn.get(num));
				restoreBtn.remove(num);
				deleteBtn.remove(num);
			}
		}
		this.revalidate();
	}
}
