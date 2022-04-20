package NoteBackEnd;

import java.time.LocalDateTime;

public interface NoteComposite {
	public void update(String title, String text, LocalDateTime date);
	public void recycle();
	public void restore();
	public void toggleCompletion();
}
