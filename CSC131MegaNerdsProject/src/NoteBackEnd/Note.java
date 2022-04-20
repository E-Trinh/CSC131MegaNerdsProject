package NoteBackEnd;

import java.util.*;
import java.time.*;

public class Note implements NoteComposite {
	private String title;
	private String text;
	private LocalDateTime date;
	private LocalDateTime lastModification;
	private LocalDateTime creation;
	private Boolean completed;
	private Boolean active;
	
	Note(String title, String text, LocalDateTime date) {
		this.title = title;
		this.text = text;
		this.date = date;
		completed = false;
		active = true;
		lastModification = LocalDateTime.now();
		creation = LocalDateTime.now();
	}
	
	Note(String title, String text) {
		this.title = title;
		this.text = text;
		this.date = null;
		completed = false;
		active = true;
		lastModification = LocalDateTime.now();
		creation = LocalDateTime.now();
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getText() {
		return text;
	}
	
	public LocalDateTime getTime() {
		return date;
	}
	
	public LocalDateTime getCreation() {
		return creation;
	}
	
	public LocalDateTime getModification() {
		return lastModification;
	}
	
	public void update(String title, String text, LocalDateTime date) {
		this.title = title;
		this.text = text;
		this.date = date;
		lastModification = LocalDateTime.now();
	}
	
	public void recycle() {
		active = false;
	}
	
	public void restore() {
		active = true;
	}
	
	public void toggleCompletion() {
		active = !active;
	}
}
