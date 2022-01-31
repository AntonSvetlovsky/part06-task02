package by.epam.jonline_introduction.part06.task02.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.epam.jonline_introduction.part06.task02.bean.Note;
import by.epam.jonline_introduction.part06.task02.bean.NoteBook;
import by.epam.jonline_introduction.part06.task02.bean.Result;
import by.epam.jonline_introduction.part06.task02.dao.DAOProvider;
import by.epam.jonline_introduction.part06.task02.dao.NoteBookDAO;
import by.epam.jonline_introduction.part06.task02.service.NoteBookService;

public class NoteBookServiceImpl implements NoteBookService {

	private final DAOProvider provider = DAOProvider.getInstance();
	private final NoteBookDAO noteBookDAO = provider.getNoteBookDAO();

	@Override
	public String addNote(String subject, String email, String message) {
		String response = null;
		if (subject == null || email == null || message == null) {
			response = Result.INVALID_INPUT.toString();
			return response;
		}
		Note note = new Note(subject, email, message);
		NoteBook noteBook = noteBookDAO.loadNoteBook();
		noteBook.addNote(note);
		response = Result.SUCCESSFUL.toString();
		return response;
	}

	@Override
	public String removeNote(String strId) {
		String response = null;
		if (strId == null || !strId.matches("\\d+")) {
			response = Result.INVALID_INPUT.toString();
			return response;
		}
		Integer key = Integer.valueOf(strId);
		NoteBook noteBook = noteBookDAO.loadNoteBook();
		if (noteBook.getNoteMap().containsKey(key)) {
			noteBook.removeNote(key);
			response = Result.SUCCESSFUL.toString();
		} else {
			response = Result.NO_MATCHES_FOUND.toString();
		}

		return response;
	}

	@Override
	public String showAllNotes() {
		String response = null;
		NoteBook noteBook = noteBookDAO.loadNoteBook();
		List<Note> noteList = new ArrayList<Note>(noteBook.getNoteMap().values());
		if (noteList.size() == 0) {
			response = Result.EMPTY.toString();
			return response;
		}
		sortNotesByDate(noteList);
		sortNotesBySubject(noteList);
		response = Result.SUCCESSFUL + "\n";
		for (Note note : noteList) {
			response += note.getId() + "|" + note.getSubject() + "|" + note.getDate() + "|" + note.getEmail() + "|"
					+ note.getMessage() + "\n";
		}
		return response;
	}

	@Override
	public String findNoteByDate(String strDate) {
		String response = null;
		LocalDate date;
		NoteBook noteBook;
		List<Note> noteList;
		List<Note> requestedList;
		if (strDate == null) {
			response = Result.INVALID_INPUT.toString();
			return response;
		}

		try {
			date = LocalDate.parse(strDate);
		} catch (DateTimeParseException e) {
			response = Result.INVALID_INPUT.toString();
			return response;
		}
		noteBook = noteBookDAO.loadNoteBook();
		noteList = new ArrayList<Note>(noteBook.getNoteMap().values());
		if (noteList.size() == 0) {
			response = Result.EMPTY.toString();
			return response;
		}
		requestedList = new ArrayList<Note>();
		for (Note note : noteList) {
			if (note.getDate().isEqual(date)) {
				requestedList.add(note);
			}
		}
		if (requestedList.size() == 0) {
			response = Result.NO_MATCHES_FOUND.toString();
			return response;
		}
		sortNotesBySubject(requestedList);
		response = Result.SUCCESSFUL + "\n";
		for (Note note : requestedList) {
			response += note.getId() + "|" + note.getSubject() + "|" + note.getDate() + "|" + note.getEmail() + "|"
					+ note.getMessage() + "\n";
		}
		return response;
	}

	@Override
	public String findNoteBySubject(String subject) {
		String response = null;
		NoteBook noteBook;
		List<Note> noteList;
		List<Note> requestedList;
		if (subject == null) {
			response = Result.INVALID_INPUT.toString();
			return response;
		}
		noteBook = noteBookDAO.loadNoteBook();
		noteList = new ArrayList<Note>(noteBook.getNoteMap().values());
		if (noteList.size() == 0) {
			response = Result.EMPTY.toString();
			return response;
		}
		requestedList = new ArrayList<Note>();
		for (Note note : noteList) {
			if (note.getSubject().equalsIgnoreCase(subject)) {
				requestedList.add(note);
			}
		}
		if (requestedList.size() == 0) {
			response = Result.NO_MATCHES_FOUND.toString();
			return response;
		}
		sortNotesByDate(requestedList);
		response = Result.SUCCESSFUL + "\n";
		for (Note note : requestedList) {
			response += note.getId() + "|" + note.getSubject() + "|" + note.getDate() + "|" + note.getEmail() + "|"
					+ note.getMessage() + "\n";
		}
		return response;
	}

	@Override
	public String findNoteByEmail(String email) {
		String response = null;
		NoteBook noteBook;
		List<Note> noteList;
		List<Note> requestedList;
		if (email == null) {
			response = Result.INVALID_INPUT.toString();
			return response;
		}
		noteBook = noteBookDAO.loadNoteBook();
		noteList = new ArrayList<Note>(noteBook.getNoteMap().values());
		if (noteList.size() == 0) {
			response = Result.EMPTY.toString();
			return response;
		}
		requestedList = new ArrayList<Note>();
		for (Note note : noteList) {
			if (note.getEmail().equalsIgnoreCase(email)) {
				requestedList.add(note);
			}
		}
		if (requestedList.size() == 0) {
			response = Result.NO_MATCHES_FOUND.toString();
			return response;
		}
		sortNotesByDate(requestedList);
		sortNotesBySubject(requestedList);
		response = Result.SUCCESSFUL + "\n";
		for (Note note : requestedList) {
			response += note.getId() + "|" + note.getSubject() + "|" + note.getDate() + "|" + note.getEmail() + "|"
					+ note.getMessage() + "\n";
		}
		return response;
	}

	@Override
	public String findNoteBySubjectAndDateAfter(String subject, String strDate) {
		String response = null;
		LocalDate date;
		NoteBook noteBook;
		List<Note> noteList;
		List<Note> requestedList;

		if (subject == null || strDate == null) {
			response = Result.INVALID_INPUT.toString();
			return response;
		}

		try {
			date = LocalDate.parse(strDate);
		} catch (DateTimeParseException e) {
			response = Result.INVALID_INPUT.toString();
			return response;
		}
		noteBook = noteBookDAO.loadNoteBook();
		noteList = new ArrayList<Note>(noteBook.getNoteMap().values());
		if (noteList.size() == 0) {
			response = Result.EMPTY.toString();
			return response;
		}
		requestedList = new ArrayList<Note>();
		for (Note note : noteList) {
			if (note.getSubject().equalsIgnoreCase(subject) && note.getDate().isAfter(date)) {
				requestedList.add(note);
			}
		}
		if (requestedList.size() == 0) {
			response = Result.NO_MATCHES_FOUND.toString();
			return response;
		}
		sortNotesByDate(requestedList);
		response = Result.SUCCESSFUL + "\n";
		for (Note note : requestedList) {
			response += note.getId() + "|" + note.getSubject() + "|" + note.getDate() + "|" + note.getEmail() + "|"
					+ note.getMessage() + "\n";
		}
		return response;
	}

	@Override
	public String findNoteByWordInMessage(String word) {
		String response = null;
		NoteBook noteBook;
		List<Note> noteList;
		List<Note> requestedList;
		if (word == null) {
			response = Result.INVALID_INPUT.toString();
			return response;
		}
		noteBook = noteBookDAO.loadNoteBook();
		noteList = new ArrayList<Note>(noteBook.getNoteMap().values());
		if (noteList.size() == 0) {
			response = Result.EMPTY.toString();
			return response;
		}
		requestedList = new ArrayList<Note>();
		Pattern pattern = Pattern.compile(".*" + word + ".*");
		Matcher matcher;
		for (Note note : noteList) {
			matcher = pattern.matcher(note.getMessage());
			if (matcher.find()) {
				requestedList.add(note);
			}
		}
		if (requestedList.size() == 0) {
			response = Result.NO_MATCHES_FOUND.toString();
			return response;
		}
		sortNotesByDate(requestedList);
		sortNotesBySubject(requestedList);
		response = Result.SUCCESSFUL + "\n";
		for (Note note : requestedList) {
			response += note.getId() + "|" + note.getSubject() + "|" + note.getDate() + "|" + note.getEmail() + "|"
					+ note.getMessage() + "\n";
		}
		return response;
	}

	@Override
	public String saveNoteBook() {
		String response = Result.EMPTY.toString();
		NoteBook noteBook = noteBookDAO.loadNoteBook();
		if (noteBook.getNoteMap().size() > 0) {
			noteBookDAO.saveNoteBook();
			response = Result.SUCCESSFUL.toString();
		}
		return response;
	}

	private void sortNotesByDate(List<Note> noteList) {
		noteList.sort(Comparator.comparing((Note note) -> note.getDate()));
	}

	private void sortNotesBySubject(List<Note> noteList) {
		noteList.sort(Comparator.comparing((Note note) -> note.getSubject()));
	}

}
