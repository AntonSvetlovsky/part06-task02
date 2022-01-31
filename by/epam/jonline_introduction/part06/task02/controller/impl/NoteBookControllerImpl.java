package by.epam.jonline_introduction.part06.task02.controller.impl;

import by.epam.jonline_introduction.part06.task02.bean.Result;
import by.epam.jonline_introduction.part06.task02.controller.Command;
import by.epam.jonline_introduction.part06.task02.controller.CommandProvider;
import by.epam.jonline_introduction.part06.task02.controller.NoteBookController;

public class NoteBookControllerImpl implements NoteBookController {

	private final CommandProvider provider = new CommandProvider();

	@Override
	public String doAction(String request) {
		String[] params = new String[2];
		String[] tmpArray;
		String commandName;
		String response;

		request = request.trim();
		tmpArray = request.split(",", 2);
		for (int i = 0; i < tmpArray.length; i++) {
			params[i] = tmpArray[i].trim();
		}
		commandName = params[0];
		Command currentCommand = provider.getCommand(commandName);

		if (currentCommand == null) {
			response = Result.INVALID_COMMAND.toString();
			return response;
		}

		response = currentCommand.execute(params[1]);

		return response;
	}

}
