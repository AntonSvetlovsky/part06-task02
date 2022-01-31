package by.epam.jonline_introduction.part06.task02.dao;

import by.epam.jonline_introduction.part06.task02.bean.NoteBook;

public interface NoteBookDAO {

	NoteBook loadNoteBook();

	void saveNoteBook();
}
