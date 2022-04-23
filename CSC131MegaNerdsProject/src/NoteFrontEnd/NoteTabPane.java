package NoteFrontEnd;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import NoteBackEnd.*;

/*
 * NoteTabPane extend JTabbedPane implements ChangeListener
 * Contains the panes for the each view
 */

public class NoteTabPane extends JTabbedPane implements ChangeListener{

	private NoteBoard noteBoard;
	private Dashboard dash;
	private Calendar calendar;
	private RecyclePane archive;
	
	//constructor, accepts reference to NoteList object, creates new instance of NoteTabPane
	public NoteTabPane(NotesList data) {
		//creates and adds new instance of each pane to the instance
		noteBoard = new NoteBoard(data);
		dash = new Dashboard(data);
		calendar = new Calendar(data);
		archive = new RecyclePane(data);
		this.add("Dashboard", dash);
		this.add("Notes", noteBoard);
		this.add("Calendar", calendar);
		this.add("Archive", archive);
		this.addChangeListener(this);
	}

	//refreshing the panes within the NoteTabPane instance whenever the tab is switched
	public void stateChanged(ChangeEvent e) {
		noteBoard.refresh();
		archive.refresh();
	}
}
