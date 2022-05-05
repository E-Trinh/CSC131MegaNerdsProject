package NoteBackEnd;

import java.util.ArrayList;

public class AtoZSort implements NoteSort{
	@Override
	public ArrayList<Note> sort(ArrayList<Note> notes) {
		return compare(notes);
	}
	
	public ArrayList<Note> compare(ArrayList<Note> toSort) {
		Note noteTemp;
		
																													// check if passed arraylist is larger than 1
			for (int i = 0; i < toSort.size() - 1; i++) {															// outer loop of bubble sort
				for( int j = 0; j < toSort.size() - i - 1; j++) {													// inner loop of bubble sort
					if (toSort.get(j).getTitle().compareTo(toSort.get(j+1).getTitle()) >= 0)							// if note1 comes before note2 alphabetically
					{
						noteTemp = toSort.get(j);												
						toSort.set(j, toSort.get(j+1));
						toSort.set(j+1, noteTemp);
					}
				}
			

			}
			return toSort;																							// return sorted arraylist
	}
	}

