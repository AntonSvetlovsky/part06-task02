package by.epam.jonline_introduction.part06.task02.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class NoteBook {

	private static AtomicInteger idCounter;
	private Map<Integer, Note> noteMap;

	static {
		idCounter = new AtomicInteger(0);
	}

	{
		noteMap = new HashMap<Integer, Note>();
	}

	public NoteBook() {
	}

	public NoteBook(Map<Integer, Note> noteMap) {
		this.noteMap = noteMap;
	}

	public static AtomicInteger getIdCounter() {
		return idCounter;
	}

	public static void setIdCounter(AtomicInteger idCounter) {
		NoteBook.idCounter = idCounter;
	}

	public Map<Integer, Note> getNoteMap() {
		return noteMap;
	}

	public void setNoteMap(Map<Integer, Note> noteMap) {
		this.noteMap = noteMap;
	}

	public void addNote(Note note) {
		int id = idCounter.incrementAndGet();
		note.setId(Integer.valueOf(id));
		noteMap.put(Integer.valueOf(id), note);
	}

	public void removeNote(Integer id) {
		noteMap.remove(id);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((noteMap == null) ? 0 : noteMap.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		NoteBook other = (NoteBook) obj;
		if (noteMap == null) {
			if (other.noteMap != null) {
				return false;
			}
		} else if (!noteMap.equals(other.noteMap)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "NoteBook [noteMap=" + noteMap + "]";
	}

}
