package NoteBackEnd;

import java.time.*;
import java.util.*;

/* Class: NotesList
 * Contains the list of Note objects for both the active notes and recycle bin
 * Factory for Note Object
 */

public class NotesList {
	
	//two ArrayList, notes contains the list of active notes, recycleBin contains the list of recycled notes
	private ArrayList<Note> notes;
	private ArrayList<Note> recycleBin;
	
	//the way that the notes are ordered in the list
	private NoteSort sortStrategy;
	
	//default constructor, initializing two lists and sorting strategy
	public NotesList() {
		notes = new ArrayList<Note>();
		recycleBin = new ArrayList<Note>();
		sortStrategy = new NewToOldSort();
	}
	
	//accepts an int and return a reference to a Note object at the corresponding index in notes list
	public Note get(int index) {
		return notes.get(index);
	}
	
	//accepts an int and returns a reference to a Note object at the corresponding index in the recycleBin list
	public Note recycleBinGet(int index) {
		return recycleBin.get(index);
	}
	
	//method stub, not implemented yet
	public Boolean importNote() {
		return null;
	}
	
	//method stub, not implemented yet
	public Boolean exportNote() {
		return null;
	}
	
	//accepts two String and LocalDateTime and returns Boolean, creates a new Note and adds it to the notes list
	public Boolean addNote(String title, String text, LocalDateTime date) {
		try {
			if (date == null) {
				notes.add(new Note(title, text));
			} else {
				notes.add(new Note(title, text, date));
			}
			return true;
		} catch (Exception e) {
			System.out.print(e);
			return false;
		}
	}
	
	//no return value, accepts an int, moves the reference to the Note at the index from the notes list to the recycleBin list
	public void recycleNote(int index) {
		recycleBin.add(notes.get(index));
		notes.remove(index);
	}
	
	//no return value, accepts an int, moves the reference to the Note at the index from the recycleBin list to the notes list
	public void restoreNote(int index) {
		notes.add(recycleBin.get(index));
		recycleBin.remove(index);
	}
	
	//no return value, accepts an int, and removes the reference to the Note from the recycleBin list
	public void delete(int index) {
		recycleBin.remove(index);
	}
	
	//no parameter or return, removes all reference to Note objects from the recycleBin list
	public void deleteAll() {
		recycleBin.clear();
	}
	
	//method stub, not implemented yet
	public void sortNote(NoteSort sortingStrategy) {
		this.sortStrategy = sortingStrategy;
		notes = sortStrategy.sort(notes);
	}
	
	//method stub, not implemented yet
	public int searchNote(String key) {
		return 0;
	}
	
	//no parameter, returns an int for the size of the  notes list
	public int size() {
		return notes.size();
	}
	
	
	//no parameter, returns an int for the size of the recycleBin list
	public int recycleBinSize() {
		return recycleBin.size();
	}
	
	//no parameter, returns an Iterator object for the  notes list
	public Iterator<Note> iterator() {
		return notes.iterator();
	}
}
