package BackTest;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import NoteBackEnd.*;

public class NotesListTest {
	
	//Checking if when creating notes, fields have the proper data
	@Test
	public void noteCreationTest() {
		//testing the constructors for notes and get methods
		NotesList data = new NotesList();
		String title = "test case title";
		String text = "test case text";
		data.addNote(title, text, "2015-08-04T10:11:30");
		assertEquals("data.get(0).getTitle()", title, data.get(0).getTitle());
		assertEquals("data.get(0).geText()", text, data.get(0).getText());
		//testing the update method
		title = "test case 2 title";
		text = "test case 2 text";
		data.get(0).update(title, text, "2015-08-04T10:11:30");
		assertEquals("Update: data.get(0).getTitle()", title, data.get(0).getTitle());
		assertEquals("Update: data.get(0).geText()", text, data.get(0).getText());
	}

	//Checking if notes are being properly recycled, restored, and deleted
	@Test
	public void noteRecycleTest() {
		//testing the recycle methods
		NotesList data = new NotesList();
		String title = "test case title";
		String text = "test case text";
		data.addNote(title, text, "2015-08-04T10:11:30");
		data.recycleNote(0);
		assertEquals("data.recycleBinGet(0).getTitle()", title, data.recycleBinGet(0).getTitle());
		//testing the restore method
		data.restoreNote(0);
		assertEquals("data.Get(0).getTitle()", title, data.get(0).getTitle());
		//testing the delete methods
		data.addNote(title, text, "2015-08-04T10:11:30");
		data.recycleNote(0);
		data.delete(0);
		data.directDelete(0);
		assertEquals("data.size()", 0, data.size());
		assertEquals("data.recycleBinSize()", 0, data.recycleBinSize());
		data.addNote(title, text, "2015-08-04T10:11:30");
		data.recycleNote(0);
		data.deleteAll();
		assertEquals("data.deleteAll()", 0, data.recycleBinSize());
	}
	
	//Checking if notes are being properly imported and exported
	@Test
	public void noteimportexportTest() {
		//adding notes to the NotesList instance
		String title = "test case title";
		String text = "test case text";
		NotesList data = new NotesList();
		String date = "2015-08-04T10:11:30";
		LocalDateTime date2 = LocalDateTime.parse("2015-08-04T10:11:30");
		data.addNote(title, text, date);
		data.addNote(title, text, date);
		data.recycleNote(1);
		//exporting notes
		data.exportNote();
		data.clearAll();
		data.importNote();
		//checking if notes are properly imported
		assertEquals("data.get(0).getTitle()", title, data.get(0).getTitle());
		assertEquals("data.get(0).geText()", text, data.get(0).getText());
		assertEquals("data.get(0).getDate()", date2, data.get(0).getDate());
		assertEquals("data.recycleBinGet(0).getTitle()", title, data.recycleBinGet(0).getTitle());
		assertEquals("data.recycleBinGet(0).geText()", text, data.recycleBinGet(0).getText());
		assertEquals("data.recycleBinGet(0).getDate()", date2, data.recycleBinGet(0).getDate());
		//testing when data file does not exist
		data.setPath("test");
		assertEquals("data.importNote()", false, data.importNote());
		//testing when cannot export note to file
		data.setPath("C:/");
		assertEquals("data.exportNote()", false, data.exportNote());
	}
	
	//Checking if the search is working
	@Test
	public void noteSearchTest() {
		//Checking the import and export methods
		NotesList data = new NotesList();
		String key = "test";
		data.addNote("test", "String", "2015-08-04T10:11:30");
		data.addNote("testtest", "String", "2015-08-04T10:11:30");
		data.addNote("testtesttest", "String", "2015-08-04T10:11:30");
		data.addNote("no", "String", "2015-08-04T10:11:30");
		//testing search method with a key
		assertEquals("data.searchNote(key).size()", 3, data.searchNote(key).size());
		//testing search  method when no key is present
		key = "";
		assertEquals("data.searchNote(key).size()", 0, data.searchNote(key).size());
	}
}
