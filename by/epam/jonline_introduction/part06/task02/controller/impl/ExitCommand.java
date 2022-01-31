package by.epam.jonline_introduction.part06.task02.controller.impl;

import by.epam.jonline_introduction.part06.task02.bean.Result;
import by.epam.jonline_introduction.part06.task02.controller.Command;
import by.epam.jonline_introduction.part06.task02.service.NoteBookService;
import by.epam.jonline_introduction.part06.task02.service.ServiceProvider;

public class ExitCommand implements Command {

	@Override
	public String execute(String params) {
		ServiceProvider provider = ServiceProvider.getInstance();
		NoteBookService service = provider.getNoteBookService();

		service.saveNoteBook();

		return Result.EXIT.toString();
	}

}
