package NoteFrontEnd;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import NoteBackEnd.*;

/*
 * Search extends JPanel
 * Contains the GUI panel for the list of active notes
 */

public class Search extends JPanel{
        
        //contains a reference to the NotesList list
        private NotesList data;
        private NotesList filteredData = new NotesList();
        
        //used for storing the two sets of button
        private ArrayList<NoteButton> filteredBtn = new ArrayList<NoteButton>();
        private ArrayList<Note> filtered;
        
        Font font = new Font("Courier", Font.BOLD, 12);
        
        //int used for storing the current page number
        private int pageNumber = 0;
        
        //int used for the number of notes on each page
        private int notePerPage = 10;
        
        //takes a reference to NotesList as parameter, constructor
        public Search(NotesList data) {
                super();
                this.data = data;
                setupLayout();
                this.setVisible(true);
        }
        
        //returns and accepts nothing, used for initial layout setup of the frame
        public void setupLayout() {
                //initializing and setting up a grid layout manager;
                GridLayout grid = new GridLayout(0, 1);

                //Setting up components
                Label searchCommand = new Label("Search by Note Title:");
                JTextField searchBar = new JTextField();
                Label titleLabel = new Label("Notes:");
                Button searchButton = new Button("Search");
                
                //sets frame to use the grid layout manager previously created
                this.setLayout(grid);
                
                searchBar.setPreferredSize(new Dimension(200,25));
                searchCommand.setAlignment(Label.CENTER);
                searchCommand.setFont(font);
                searchButton.setBackground(Color.green);
                titleLabel.setBackground(Color.yellow);
                titleLabel.setAlignment(Label.CENTER);
                
                this.add(searchCommand);
                this.add(searchBar);
                this.add(searchButton);
                this.add(titleLabel);

                //Adds anonymous method for event handling to create a new note
                searchButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                while (filteredData.size() > 0) {
                                        filteredData.directDelete(0);  //for resetting search criteria
                                }
                                filtered = data.searchNote(searchBar.getText());
                                if(filtered != null) {
                                        for(int i = 0 ; i < filtered.size(); i++) {
                                                filteredData.addNote(filtered.get(i).getTitle(), filtered.get(i).getText(), null);
                                        }
                                }
                                refresh();
                        }
                });
                
                //instantiating needed buttons
                for (int i = 0; i < notePerPage; i++) {
                        filteredBtn.add(new NoteButton());
                        this.add(filteredBtn.get(i));
                        filteredBtn.get(i).setFont(font);
                }
                
                Button forwardPage = new Button("Forward");
                //adds ActionListener with anonymous method for showing the next page of notes
                forwardPage.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                if ((pageNumber + 1) * notePerPage < filteredData.size()) {
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
                        if (notePerPage * pageNumber + i < filteredData.size()) {
                                filteredBtn.get(i).setNote(filteredData.get(notePerPage*pageNumber+i));
                        } else {
                                filteredBtn.get(i).removeHide();
                        }
                }
        }
}
