package by.epam.jonline_introduction.part06.task02.dao.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import by.epam.jonline_introduction.part06.task02.bean.Note;
import by.epam.jonline_introduction.part06.task02.bean.NoteBook;
import by.epam.jonline_introduction.part06.task02.dao.NoteBookDAO;

public class NoteBookDAOImpl implements NoteBookDAO {

	private NoteBook noteBook;

	@Override
	public NoteBook loadNoteBook() {
		if (noteBook == null) {
			noteBook = new NoteBook(loadNoteMap());
		}
		return noteBook;
	}

	private Map<Integer, Note> loadNoteMap() {
		Map<Integer, Note> noteMap = new HashMap<Integer, Note>();
		String[] tmpArray;
		String tmp;
		try (BufferedReader reader = new BufferedReader(new FileReader("resources\\noteBook.txt"))) {

			tmp = reader.readLine();
			if (tmp != null) {
				NoteBook.setIdCounter(new AtomicInteger(Integer.valueOf(tmp)));
			}
			while ((tmp = reader.readLine()) != null) {
				tmpArray = tmp.split(",", 5);
				noteMap.put(Integer.valueOf(tmpArray[0]), new Note(Integer.valueOf(tmpArray[0]), tmpArray[1],
						LocalDate.ofEpochDay(Long.parseLong(tmpArray[2])), tmpArray[3], tmpArray[4]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return noteMap;
	}

	@Override
	public void saveNoteBook() {
		Map<Integer, Note> noteMap = noteBook.getNoteMap();
		List<Note> noteList = new ArrayList<Note>(noteMap.values());
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources\\noteBook.txt"))) {
			writer.write(NoteBook.getIdCounter().toString());
			writer.newLine();
			for (Note note : noteList) {
				writer.write(note.getId() + "," + note.getSubject() + "," + note.getDate().toEpochDay() + ","
						+ note.getEmail() + "," + note.getMessage());
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
