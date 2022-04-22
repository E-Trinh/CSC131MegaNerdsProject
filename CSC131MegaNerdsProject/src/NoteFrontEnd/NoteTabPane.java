package NoteFrontEnd;

import javax.swing.*;
import NoteBackEnd.*;

public class NoteTabPane extends JTabbedPane{

	private NotesList data;
	
	public NoteTabPane(NotesList data) {
		this.data = data;
		setupLayout();
	}
	
	public void setupLayout() {
		NoteBoard noteBoard = new NoteBoard(data);
		Dashboard dash = new Dashboard(data);
		Calendar calendar = new Calendar(data);
		this.add("Dashboard", dash);
		this.add("Notes", noteBoard);
		this.add("Calendar", calendar);
	}
}
