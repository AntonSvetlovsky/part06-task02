package by.epam.jonline_introduction.part06.task02.service;

public interface NoteBookService {

	String addNote(String subject, String email, String message);

	String removeNote(String strId);

	String showAllNotes();

	String findNoteByDate(String date);

	String findNoteBySubject(String subject);

	String findNoteByEmail(String email);

	String findNoteBySubjectAndDateAfter(String subject, String date);

	String findNoteByWordInMessage(String word);

	String saveNoteBook();
}
