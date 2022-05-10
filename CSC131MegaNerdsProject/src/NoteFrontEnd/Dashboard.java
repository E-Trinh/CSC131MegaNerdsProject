package NoteFrontEnd;

import javax.swing.*;
import NoteBackEnd.*;

public class Dashboard extends JPanel{
	private NotesList data;
	private int pageNumber = 0;
	private int notePerPage = 10;
	private Label pageNumberLabel;
	
	private ArrayList<NoteButton> noteBtn = new ArrayList<NoteButton>();
	
	public Dashboard(NotesList data) {
		this.data = data;
	}
	public void setupLayout() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints constraintTitle = new GridBagConstraints();
		
		Label titleLabel = new Label("Dashboard");
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
		
		Button newNote = new Button("Recently Opened Notes");
		newNote.setBackground(Color.green);
		Button newNote1 = new Button("Pinned Notes");
		newNote1.setBackground(Color.green);
		
		

	}
	Button forwardPage = new Button("Forward");
	forwardPage.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if ((pageNumber + 1) * notePerPage < data.size()) {
				pageNumber++;
				updateButton();
			}
		}
	});
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
	public void refresh() {
		pageNumber = 0;
		updateButton();
	}
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
}

