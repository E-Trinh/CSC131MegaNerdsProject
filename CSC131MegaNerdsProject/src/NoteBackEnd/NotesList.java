package NoteBackEnd;

import java.time.*;
import java.util.*;

public class NotesList {
	private ArrayList<Note> notes;
	private NoteSort sortStrategy;
	
	public NotesList() {
		notes = new ArrayList<Note>();
		sortStrategy = new NewToOldSort();
	}
	
	public Note get(int index) {
		return notes.get(index);
	}
	
	public Boolean importNote() {
		return null;
	}
	
	public Boolean exportNote() {
		return null;
	}
	
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
	
	public Boolean deleteNote(int index) {
		try {
			notes.remove(index);
			return true;
		} catch (Exception e) {
			System.out.print(e);
			return false;
		}
	}
	
	public void sortNote(NoteSort sortingStrategy) {
		this.sortStrategy = sortingStrategy;
		notes = sortStrategy.sort(notes);
	}
	
	public int searchNote(String key) {
		return 0;
	}
	
	public int size() {
		return notes.size();
	}
}
