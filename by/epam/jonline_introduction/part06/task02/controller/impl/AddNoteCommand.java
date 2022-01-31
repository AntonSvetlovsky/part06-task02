package by.epam.jonline_introduction.part06.task02.controller.impl;

import by.epam.jonline_introduction.part06.task02.controller.Command;
import by.epam.jonline_introduction.part06.task02.service.NoteBookService;
import by.epam.jonline_introduction.part06.task02.service.ServiceProvider;

public class AddNoteCommand implements Command {

	@Override
	public String execute(String params) {
		ServiceProvider provider = ServiceProvider.getInstance();
		NoteBookService service = provider.getNoteBookService();

		String subject;
		String email;
		String message;

		String[] paramsArray = new String[3];
		if (params != null) {
			String[] tmpArray = params.split(",", 3);
			for (int i = 0; i < tmpArray.length; i++) {
				paramsArray[i] = tmpArray[i].trim();
			}
		}

		subject = paramsArray[0];
		email = paramsArray[1];
		message = paramsArray[2];

		return service.addNote(subject, email, message);
	}

}
