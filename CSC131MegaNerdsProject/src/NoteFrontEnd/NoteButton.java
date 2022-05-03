package NoteFrontEnd;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

import javax.swing.*;

import NoteBackEnd.*;

/*
 * NoteButton extends button implements ActionListener
 */

public class NoteButton extends Button implements ActionListener {
	
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
	public void actionPerformed(ActionEvent e) 
	{
		//setting up a panel to pass to dialog box
		GridBagLayout dialogLayout = new GridBagLayout();
		JPanel panel = new JPanel(dialogLayout);
		panel.setPreferredSize(new Dimension(700,500));
		JLabel titleLabel = new JLabel("Title:");
		JLabel textLabel = new JLabel("Text:");
		JLabel dateLabel = new JLabel("Date:");
		JLabel timeLabel  = new JLabel("Time:");
		JLabel completedLabel = new JLabel("Complete:");
		JTextField titleBox = new JTextField(note.getTitle());
		titleBox.setHorizontalAlignment(JTextField.LEFT);
		JTextArea textBox = new JTextArea(note.getText());
		textBox.setLineWrap(true);
		
		String day = String.format("%d", note.getDate().getYear()) + "/" + String.format("%02d", note.getDate().getMonth().getValue()) + "/" + String.format("%02d", note.getDate().getDayOfMonth());
		String time = String.format("%02d", note.getDate().getHour()) + ":" + String.format("%02d", note.getDate().getMinute()) + ":" + String.format("%02d", note.getDate().getSecond());
		
		JTextField dateBox = new JTextField(day);	
		JTextField timeBox = new JTextField(time);
		
		
		JCheckBox completedBox = new JCheckBox();
		completedBox.setText("");
		completedBox.setSelected(note.getCompleted());
		
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
		constraintDialog.gridx = 0;
		constraintDialog.gridy = 3;
		constraintDialog.gridwidth = 2;
		constraintDialog.weighty = 0.05;
		panel.add(completedLabel, constraintDialog);
		constraintDialog.gridx = 2;
		panel.add(completedBox, constraintDialog);
		
		//opens a dialog box with fields to allow the user to edit the Note
		int noteEdit = JOptionPane.showConfirmDialog(this, panel);
		
		//when Yes is selected changes are updated on the reference to the Note object
		if (noteEdit == JOptionPane.YES_OPTION) 
		{
			String temp = formatString(dateBox.getText(), timeBox.getText());
			
			this.note.update(titleBox.getText(), textBox.getText(), temp);
			
			if (completedBox.getSelectedObjects() == null) 
			{
                this.note.toggleCompletion(false);
            } 
			else 
			{
                this.note.toggleCompletion(true);
            }
			
			this.setLabel(note.getTitle());
		}
	}
	
	//2015/10/04
	//2015-08-04T10:11:30
	//10:11:30
	private String formatString(String date, String time) 
	{
		String temp = date;
		String y = time;
		
		String temp2 = "";
		String temp3 = "";
		String temp4 = "";
		
		String temp5 = "";
		String temp6 = "";
		String temp7 = "";
		
		String result = "";

		temp2 = temp.substring(0,4); //year
		temp3 = temp.substring(5,7); //month
		temp4 = temp.substring(8,temp.length()); //day
		
		temp5 = y.substring(0,2);//hour
		temp6 = y.substring(3,5);//minute
		temp7 = y.substring(6,8);//seconds

		result = temp2 + "-" + temp3 + "-" + temp4 + "T" + temp5 + ":" + temp6 + ":" + temp7;

		return result;
	}
}