package by.epam.jonline_introduction.part06.task02.controller.impl;

import by.epam.jonline_introduction.part06.task02.controller.Command;
import by.epam.jonline_introduction.part06.task02.service.NoteBookService;
import by.epam.jonline_introduction.part06.task02.service.ServiceProvider;

public class FindNoteBySubjectAndDateAfterCommand implements Command {

	@Override
	public String execute(String params) {
		ServiceProvider provider = ServiceProvider.getInstance();
		NoteBookService service = provider.getNoteBookService();

		String subject;
		String date;

		String[] paramsArray = new String[2];
		if (params != null) {
			String[] tmpArray = params.split(",", 2);
			for (int i = 0; i < tmpArray.length; i++) {
				paramsArray[i] = tmpArray[i].trim();
			}
		}

		subject = paramsArray[0];
		date = paramsArray[1];

		return service.findNoteBySubjectAndDateAfter(subject, date);
	}

}
