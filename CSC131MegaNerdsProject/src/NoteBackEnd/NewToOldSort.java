package NoteBackEnd;

import java.util.ArrayList;

public class NewToOldSort implements NoteSort{
	@Override
	public ArrayList<Note> sort(ArrayList<Note> notes) {
		return compare(notes);
	}

	private ArrayList<Note> compare(ArrayList<Note> toSort) {
		ArrayList<Note> sorted = new ArrayList<Note>();
		Note noteTemp;
		
		
			if (sorted.size() > 1)	{																				// check if passed arraylist is larger than 1
			for (int i = 0; i < toSort.size(); i++) {																// outer loop of bubble sort
				for( int j = 0; j < toSort.size() - i - 1; j++) {													// inner loop of bubble sort
					if (toSort.get(i).getModification().isAfter(toSort.get(i+1).getModification()))					// if last modification date of note1 is after than last mod date of note2
					{
						noteTemp = toSort.get(i);												
						sorted.set(i, toSort.get(i+1));
						sorted.set(i+1, noteTemp);
					}
				}
			

			}
	}
			return sorted;																							// return sorted arraylist
	}
}
