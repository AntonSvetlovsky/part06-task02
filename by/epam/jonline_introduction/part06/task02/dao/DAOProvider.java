package by.epam.jonline_introduction.part06.task02.dao;

import by.epam.jonline_introduction.part06.task02.dao.impl.NoteBookDAOImpl;

public final class DAOProvider {

	private static final DAOProvider instance = new DAOProvider();
	private final NoteBookDAO noteBookDAO = new NoteBookDAOImpl();

	private DAOProvider() {
	}

	public static DAOProvider getInstance() {
		return instance;
	}

	public NoteBookDAO getNoteBookDAO() {
		return noteBookDAO;
	}

}
