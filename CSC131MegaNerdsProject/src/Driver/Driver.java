package Driver;

import java.time.LocalDateTime;

import NoteBackEnd.*;
import NoteFrontEnd.*;

public class Driver {

	public static void main(String[] args) {
		NotesList test = new NotesList();
		test.addNote("title", "text", LocalDateTime.now());
		test.addNote("title", "text", LocalDateTime.now());
		test.addNote("title", "text", LocalDateTime.now());
		Note ex = test.get(0);
		System.out.println(ex.getTitle() + ex.getText() + ex.getTime() + ex.getCreation() + ex.getModification());
		NoteWindow testWindow = new NoteWindow(test);
		testWindow.setVisible(true);
	}
}
