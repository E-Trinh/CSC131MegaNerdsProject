package Driver;

import NoteBackEnd.*;
import NoteFrontEnd.*;

/*
 * Driver Class for the Notes Application
 * CSC 131: Group Mega Nerds
 */

public class Driver {

	//main method
	public static void main(String[] args) {
		//new note list
		NotesList list = new NotesList();
		//new Window
		NoteWindow noteWindow = new NoteWindow(list);
		noteWindow.setVisible(true);
	}
}
