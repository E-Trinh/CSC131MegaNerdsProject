package NoteFrontEnd;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import NoteBackEnd.*;

/*
 * NoteButton extends button implements ActionListener
 */

public class NoteButton extends Button implements ActionListener{
	
	//reference to the Note object the button corresponds with
	private Note note;
	
	//defuault constructor
	public NoteButton() {
		super();
		this.addActionListener(this);
		this.setVisible(false);
	}
	
	//constructor, accepts reference to Note object
	public NoteButton(Note note) {
		super();
		this.note = note;
		this.setLabel(note.getTitle());
		this.addActionListener(this);
	}
	
	//accepts Note and no return, setter method
	public void setNote(Note note) {
		this.note = note;
		this.setLabel(note.getTitle());
		this.setVisible(true);
	}
	
	//no parameter and no return, removes reference to Note object and hides the object
	public void removeHide() {
		this.note = null;
		this.setVisible(false);
	}
 	
	//accepts ActionEvent and no return, when the button is clicked opens a dialog box to edit the fields for the button
	public void actionPerformed(ActionEvent e) {
		//setting up a panel to pass to dialog box
		JPanel panel = new JPanel();
		JLabel titleLabel = new JLabel("Title:");
		JLabel textLabel = new JLabel("Text:");
		JFormattedTextField titleBox = new JFormattedTextField(note.getTitle());
		JFormattedTextField textBox = new JFormattedTextField(note.getText());
		panel.add(titleLabel);
		panel.add(titleBox);
		panel.add(textLabel);
		panel.add(textBox);
		
		//opens a dialog box with fields to allow the user to edit the Note
		int noteEdit = JOptionPane.showConfirmDialog(this, panel);
		
		//when Yes is selected changes are updated on the reference to the Note object
		if (noteEdit == JOptionPane.YES_OPTION) {
			this.note.update(titleBox.getText(), textBox.getText(), null);
			this.setLabel(note.getTitle());
		}
	}
}