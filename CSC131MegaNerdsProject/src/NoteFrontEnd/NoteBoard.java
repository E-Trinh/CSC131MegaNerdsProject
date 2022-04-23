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
	
	//used for storing the two sets of button
	private ArrayList<NoteButton> noteBtn = new ArrayList<NoteButton>();
	private ArrayList<Button> delete = new ArrayList<Button>();
	
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
		
		//Setting up elements
		Label titleLabel = new Label("Notes");
		titleLabel.setBackground(Color.yellow);
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
		
		this.add(titleLabel);
		this.add(newNote);
		refresh();
	}
	
	//returns and accepts void, used for refreshing all the components in the frame
	public void refresh() {
		//when there are more notes than pairs of button, sets the label of each button to the corresponding title and creates a new pair of button for notes without buttons
		if (data.size() > noteBtn.size()) {
			for (int i = 0; i < data.size(); i++) {
				if (i < noteBtn.size()) {
					noteBtn.get(i).setLabel(data.get(i).getTitle());
				} else {
					noteBtn.add(new NoteButton(data.get(i)));
					this.add(noteBtn.get(i));
					delete.add(new Button("Recycle"));
					
					//adds ActionListener with anonymous method for recycling the note that matches with the button index
					delete.get(i).addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int deleteResult = JOptionPane.showConfirmDialog(null, "Recycle this note?");
							if (deleteResult == JOptionPane.YES_OPTION) {
								int buttonIndex = delete.indexOf(e.getSource());
								data.recycleNote(buttonIndex);
								refresh();
							}
						}
					});
					this.add(delete.get(i));
					this.revalidate();
				}
			}
		} else {
		//when there are more pair of buttons than notes, sets the title to the corresponding buttons and removes the extra
			ArrayList<Integer> nums = new ArrayList<Integer>();
			for (int i = 0; i < noteBtn.size(); i++) {
				if (i < data.size()) {
					noteBtn.get(i).setLabel(data.get(i).getTitle());
				} else {
					//adds the index of items that need to removed, the items are removed in reverse order
					nums.add(i);
				}
			}
			//removing the buttons from the frame and lists
			for (int i = nums.size() - 1; i >= 0; i--) {
				int num = nums.get(i);
				this.remove(noteBtn.get(num));
				this.remove(delete.get(num));
				noteBtn.remove(num);
				delete.remove(num);
			}
		}
	}
}
