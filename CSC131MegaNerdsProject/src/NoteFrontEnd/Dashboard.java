package NoteFrontEnd;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import NoteBackEnd.*;
import DateTimeUtil.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Dashboard extends JPanel{
	private NotesList data;
	
	private ArrayList<Label> upcomingNoteTitle = new ArrayList<Label>();
	private ArrayList<Label> incompleteNoteTitle = new ArrayList<Label>();;
	private ArrayList<Label> upcomingNoteDate = new ArrayList<Label>();;
	private ArrayList<Label> incompleteNoteDate = new ArrayList<Label>();;
	
	public Dashboard(NotesList data) {
		this.data = data;
		setupLayout();
	}
	
	public void setupLayout() {
		this.setLayout(new GridBagLayout());

		this.setBackground(new Color(41, 41, 41));
		this.setBorder(BorderFactory.createEmptyBorder());
		
		GridBagConstraints labelConstraints = new GridBagConstraints();
        Label titleLabel = new Label("Dashboard");
		titleLabel.setBackground(new Color(224, 164, 97));
		titleLabel.setForeground(new Color(102, 102, 102));
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        titleLabel.setAlignment(Label.CENTER);
        Label upcomingLabel = new Label("Upcoming");
        upcomingLabel.setBackground(new Color(224, 164, 97));
		upcomingLabel.setForeground(new Color(102, 102, 102));
        upcomingLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        upcomingLabel.setAlignment(Label.CENTER);
        Label incompleteLabel = new Label("Incomplete");
        incompleteLabel.setBackground(new Color(224, 164, 97));
        incompleteLabel.setForeground(new Color(102, 102, 102));
        incompleteLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        incompleteLabel.setAlignment(Label.CENTER);
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;
		labelConstraints.gridheight = 1;
		labelConstraints.gridwidth = 2;
		labelConstraints.weightx = 1;
		labelConstraints.weighty = 1;
		labelConstraints.fill = GridBagConstraints.BOTH;
		labelConstraints.insets = new Insets(2, 10, 2, 10);
        this.add(titleLabel, labelConstraints);
        labelConstraints.gridy = 1;
        this.add(upcomingLabel, labelConstraints);
        labelConstraints.gridy = 6;
        this.add(incompleteLabel, labelConstraints);
        
        
        for (int i = 0; i < 4; i++) {
        	upcomingNoteTitle.add(new Label("Date"));
        	upcomingNoteTitle.get(i).setBackground(new Color(254, 249, 254));
        	upcomingNoteTitle.get(i).setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        	upcomingNoteDate.add(new Label("Text"));
        	upcomingNoteDate.get(i).setBackground(new Color(254, 249, 254));
        	upcomingNoteDate.get(i).setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
    		GridBagConstraints upcomingConstraints = new GridBagConstraints();
    		upcomingConstraints.gridx = 0;
    		upcomingConstraints.gridy = i + 2;
    		upcomingConstraints.gridheight = 1;
    		upcomingConstraints.gridwidth = 1;
    		upcomingConstraints.weightx = 0.5;
    		upcomingConstraints.weighty = 1;
    		upcomingConstraints.fill = GridBagConstraints.BOTH;
    		upcomingConstraints.insets = new Insets(2, 10, 2, 0);
    		this.add(upcomingNoteDate.get(i), upcomingConstraints);
    		upcomingConstraints.weightx = 1;
    		upcomingConstraints.gridx = 1;
    		upcomingConstraints.insets = new Insets(2, 0, 2, 10);
    		this.add(upcomingNoteTitle.get(i), upcomingConstraints);
    		
    		incompleteNoteTitle.add(new Label(""));
    		incompleteNoteTitle.get(i).setBackground(new Color(254, 249, 254));
    		incompleteNoteTitle.get(i).setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
    		incompleteNoteDate.add(new Label(""));
    		incompleteNoteDate.get(i).setBackground(new Color(254, 249, 254));
    		incompleteNoteDate.get(i).setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
    		GridBagConstraints incompleteConstraints = new GridBagConstraints();
    		incompleteConstraints.gridx = 0;
    		incompleteConstraints.gridy = i + 7;
    		incompleteConstraints.gridheight = 1;
    		incompleteConstraints.gridwidth = 1;
    		incompleteConstraints.weightx = 0.5;
    		incompleteConstraints.weighty = 1;
    		incompleteConstraints.fill = GridBagConstraints.BOTH;
    		incompleteConstraints.insets = new Insets(2, 10, 2, 0);
    		this.add(incompleteNoteDate.get(i), incompleteConstraints);
    		incompleteConstraints.weightx = 1;
    		incompleteConstraints.gridx = 1;
    		incompleteConstraints.insets = new Insets(2, 0, 2, 10);
    		this.add(incompleteNoteTitle.get(i), incompleteConstraints);
        }
	}
	
	public void refresh() { 
		
		LocalDateTime current = data.get(0).getDate();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String fixedDate = current.format(formatter);
		upcomingNoteTitle.get(0).setText(data.get(0).getTitle());
		upcomingNoteDate.get(0).setText(fixedDate);
	}
}

