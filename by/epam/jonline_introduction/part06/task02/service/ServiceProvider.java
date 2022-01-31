package by.epam.jonline_introduction.part06.task02.service;

import by.epam.jonline_introduction.part06.task02.service.impl.NoteBookServiceImpl;

public final class ServiceProvider {

	private static final ServiceProvider instance = new ServiceProvider();
	private final NoteBookService noteBookService = new NoteBookServiceImpl();

	private ServiceProvider() {
	}

	public static ServiceProvider getInstance() {
		return instance;
	}

	public NoteBookService getNoteBookService() {
		return noteBookService;
	}

}
