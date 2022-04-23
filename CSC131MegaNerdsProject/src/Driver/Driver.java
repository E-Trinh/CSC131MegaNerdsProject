package Driver;

import java.time.LocalDateTime;

import NoteBackEnd.*;
import NoteFrontEnd.*;

public class Driver {

	public static void main(String[] args) {
		NotesList test = new NotesList();
		NoteWindow testWindow = new NoteWindow(test);
		testWindow.setVisible(true);
	}
}
