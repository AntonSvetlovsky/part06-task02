package by.epam.jonline_introduction.part06.task02.controller;

import by.epam.jonline_introduction.part06.task02.controller.impl.NoteBookControllerImpl;

public final class ControllerProvider {

	private static final ControllerProvider instance = new ControllerProvider();
	private final NoteBookController controller = new NoteBookControllerImpl();

	private ControllerProvider() {
	}

	public static ControllerProvider getInstance() {
		return instance;
	}

	public NoteBookController getController() {
		return controller;
	}

}
