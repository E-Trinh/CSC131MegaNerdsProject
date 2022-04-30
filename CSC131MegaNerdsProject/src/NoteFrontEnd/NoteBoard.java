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
	
	//label used for display the current page number
	private Label pageNumberLabel;
	
	//used for storing the two sets of button
	private ArrayList<NoteButton> noteBtn = new ArrayList<NoteButton>();
	private ArrayList<Button> recycleBtn = new ArrayList<Button>();
	private ArrayList<Note> filteredData = new ArrayList<Note>();
	//takes a reference to NotesList as parameter, constructor
	public NoteBoard(NotesList data) {
		super();
		this.data = data;
		setupLayout();
		this.setVisible(true);
	}
	
	//returns and accepts nothing, used for initial layout setup of the frame
	public void setupLayout() {
		//set frame to use GridBagLayout
		this.setLayout(new GridBagLayout());
		GridBagConstraints constraintTitle = new GridBagConstraints();
		
		//Setting up components
		//Title Label component
		Label titleLabel = new Label("Notes");
		titleLabel.setBackground(Color.yellow);
		constraintTitle.gridx = 0;
		constraintTitle.gridy = 0;
		constraintTitle.gridheight = 1;
		constraintTitle.gridwidth = 4;
		constraintTitle.weightx = 1;
		constraintTitle.weighty = 0.5;
		constraintTitle.anchor = GridBagConstraints.PAGE_START;
		constraintTitle.fill = GridBagConstraints.BOTH;
		this.add(titleLabel, constraintTitle);
		
		//Create New Note Button
		Button newNote = new Button("Create New Note");
		newNote.setBackground(Color.green);
		//Adds anonymous method for event handling to create a new note
		newNote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//setting up a panel to pass to dialog box
				GridBagLayout dialogLayout = new GridBagLayout();
				JPanel panel = new JPanel(dialogLayout);
				panel.setPreferredSize(new Dimension(700,500));
				JLabel titleLabel = new JLabel("Title:");
				JLabel textLabel = new JLabel("Text:");
				JLabel dateLabel = new JLabel("Date:");
				JLabel timeLabel  = new JLabel("Time:");
				JTextField titleBox = new JTextField();
				titleBox.setHorizontalAlignment(JTextField.LEFT);
				JTextArea textBox = new JTextArea();
				textBox.setLineWrap(true);
				JTextField dateBox = new JTextField();
				JTextField timeBox = new JTextField();
				JScrollPane textBoxPane = new JScrollPane(textBox);
				
				//setting up constraints for panel layout
				GridBagConstraints constraintDialog = new GridBagConstraints();
				constraintDialog.gridx = 0;
				constraintDialog.gridy = 0;
				constraintDialog.gridheight = 1;
				constraintDialog.gridwidth = 1;
				constraintDialog.weightx = 0.25;
				constraintDialog.weighty = 0.05;
				constraintDialog.fill = GridBagConstraints.BOTH;
				panel.add(titleLabel, constraintDialog);
				constraintDialog.gridy = 2;
				panel.add(dateLabel, constraintDialog);
				constraintDialog.gridx = 2;
				panel.add(timeLabel, constraintDialog);
				constraintDialog.gridx = 0;
				constraintDialog.gridy = 1;
				constraintDialog.weighty = 1;
				panel.add(textLabel, constraintDialog);
				constraintDialog.gridx = 1;
				constraintDialog.gridy = 0;
				constraintDialog.gridheight = 1;
				constraintDialog.gridwidth = 3;
				constraintDialog.weightx = 1;
				constraintDialog.weighty = 0.05;
				constraintDialog.weightx = 1;
				panel.add(titleBox, constraintDialog);
				constraintDialog.gridy = 2;
				constraintDialog.gridwidth = 1;
				panel.add(dateBox, constraintDialog);
				constraintDialog.gridx = 3;
				panel.add(timeBox, constraintDialog);
				constraintDialog.gridwidth = 3;
				constraintDialog.gridy = 1;
				constraintDialog.gridx = 1;
				constraintDialog.weighty = 1;
				panel.add(textBoxPane, constraintDialog);
				
				//if the user presses yes on the dialog box, takes the data from the box and creates a new Note
				int noteEdit = JOptionPane.showConfirmDialog(null, panel);
				if (noteEdit == JOptionPane.YES_OPTION) {
					data.addNote(titleBox.getText(), textBox.getText(), null);
					refresh();
				}
			}
		});
		
		//Adding search bar navigation buttons
		JTextField searchBar = new JTextField();
		Button searchButton = new Button("Search");
		Button clearhButton = new Button("Clear");
		GridBagConstraints searchConstraints = new GridBagConstraints();
		searchConstraints.gridx = 0;
		searchConstraints.gridy = 1;
		searchConstraints.gridheight = 1;
		searchConstraints.gridwidth = 2;
		searchConstraints.weightx = 1;
		searchConstraints.weighty = 0.5;
		searchConstraints.anchor = GridBagConstraints.LINE_START;
		searchConstraints.fill = GridBagConstraints.BOTH;
		this.add(searchBar, searchConstraints);
		searchConstraints.gridwidth = 1;
		searchConstraints.gridx = 2;
		searchConstraints.weightx = 0.075;
		this.add(searchButton, searchConstraints);
		searchConstraints.gridx = 3;
		this.add(clearhButton, searchConstraints);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filteredData = data.searchNote(searchBar.getText());
				searchRefresh();
				}
		});
		clearhButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchBar.setText("");
				refresh();
			}
		});
		
		//new note and sort navigation buttons
		String[] sortMethods = {"A to Z", "Z to A", "Newest", "Oldest"};
		JComboBox<String> sortBox = new JComboBox<String>(sortMethods);
		GridBagConstraints constraintNew = new GridBagConstraints();
		constraintNew.gridx = 0;
		constraintNew.gridy = 2;
		constraintNew.gridheight = 1;
		constraintNew.gridwidth = 2;
		constraintNew.weightx = 1;
		constraintNew.weighty = 0.5;
		constraintNew.anchor = GridBagConstraints.LINE_START;
		constraintNew.fill = GridBagConstraints.BOTH;
		this.add(newNote, constraintNew);
		constraintNew.gridx = 2;
		constraintNew.weightx = 0.15;
		this.add(sortBox, constraintNew);
		
		sortBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.print("Sorting method selected: " + sortBox.getSelectedItem());
			}
		});
		
		//buttons for display notes and recycle notes
		ArrayList<JPanel> buttonPanel = new ArrayList<JPanel>();
		ArrayList<JPanel> recycleButtonPanel = new ArrayList<JPanel>(); 
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
			buttonPanel.add(new JPanel());
			buttonPanel.get(i).setLayout(new GridLayout(1,1));
			recycleButtonPanel.add(new JPanel());
			recycleButtonPanel.get(i).setLayout(new GridLayout(1,1));
			GridBagConstraints constraintNoteButton = new GridBagConstraints();
			constraintNoteButton.gridx = 0;
			constraintNoteButton.gridy = i+3;
			constraintNoteButton.gridheight = 1;
			constraintNoteButton.gridwidth = 2;
			constraintNoteButton.weightx = 1;
			constraintNoteButton.weighty = 1;
			constraintNoteButton.fill = GridBagConstraints.BOTH;
			buttonPanel.get(i).add(noteBtn.get(i));
			this.add(buttonPanel.get(i), constraintNoteButton);
			constraintNoteButton.gridx = 2;
			constraintNoteButton.gridwidth = 2;
			constraintNoteButton.weightx = 0.15;
			recycleButtonPanel.get(i).add(recycleBtn.get(i));
			this.add(recycleButtonPanel.get(i), constraintNoteButton);
		}
		
		//forward and previous page buttons
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
		GridBagConstraints pageConstraint = new GridBagConstraints();
		pageConstraint.gridx = 0;
		pageConstraint.gridy = 13;
		pageConstraint.gridheight = 1;
		pageConstraint.gridwidth = 1;
		pageConstraint.weightx = 1;
		pageConstraint.weighty = 0.5;
		pageConstraint.fill = GridBagConstraints.BOTH;
		this.add(previousPage, pageConstraint);
		pageConstraint.gridx = 1;
		this.add(forwardPage, pageConstraint);
		pageConstraint.gridx = 2;
		pageConstraint.gridwidth = 2;
		pageConstraint.weightx = 0.15;
		pageNumberLabel = new Label();
		pageNumberLabel.setAlignment(Label.CENTER);
		this.add(pageNumberLabel, pageConstraint);
		refresh();
	}
	
	//no parameters and return, resets the page number and updates the button
	public void refresh() {
		pageNumber = 0;
		updateButton();
	}
	
	//returns and accepts void, used for refreshing all the buttons in the panel
	public void updateButton() {
		pageNumberLabel.setText("Page Number: " + String.format("%d", pageNumber + 1));
		for (int i = 0; i < notePerPage; i++) {
			if (notePerPage * pageNumber + i < data.size()) {
				noteBtn.get(i).setNote(data.get(notePerPage*pageNumber+i));
				recycleBtn.get(i).setVisible(true);
			} else {
				noteBtn.get(i).removeHide();
				recycleBtn.get(i).setVisible(false);
			}
		}
		this.revalidate();
	}
	
	public void searchRefresh() {
                pageNumberLabel.setText("Page Number: " + String.format("%d", pageNumber + 1));
                for (int i = 0; i < notePerPage; i++) {
                        if (notePerPage * pageNumber + i < filteredData.size()) {
                                noteBtn.get(i).setNote(filteredData.get(notePerPage*pageNumber+i));
                                recycleBtn.get(i).setVisible(true);
                        } else {
                                noteBtn.get(i).removeHide();
                                recycleBtn.get(i).setVisible(false);
                        }
                }
                this.revalidate();
        }
}
