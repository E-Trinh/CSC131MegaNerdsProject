package NoteFrontEnd;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import NoteBackEnd.*;

public class NoteButton extends Button implements ActionListener{
	
	private Note note;
	
	public NoteButton(Note note) {
		super();
		this.note = note;
		setupLayout();
		this.addActionListener(this);
	}
	
	public void setupLayout() {
		this.setLabel(note.getTitle());
	}
	
	public void actionPerformed(ActionEvent e) {
		JPanel panel = new JPanel();
		JLabel titleLabel = new JLabel("Title:");
		JLabel textLabel = new JLabel("Text:");
		JFormattedTextField titleBox = new JFormattedTextField(note.getTitle());
		JFormattedTextField textBox = new JFormattedTextField(note.getText());
		panel.add(titleLabel);
		panel.add(titleBox);
		panel.add(textLabel);
		panel.add(textBox);
		int noteEdit = JOptionPane.showConfirmDialog(this, panel);
		if (noteEdit == JOptionPane.YES_OPTION) {
			this.note.update(titleBox.getText(), textBox.getText(), null);
			this.setLabel(note.getTitle());
		}
	}
}