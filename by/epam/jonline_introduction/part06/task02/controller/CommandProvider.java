package by.epam.jonline_introduction.part06.task02.controller;

import java.util.HashMap;
import java.util.Map;

import by.epam.jonline_introduction.part06.task02.controller.impl.AddNoteCommand;
import by.epam.jonline_introduction.part06.task02.controller.impl.ExitCommand;
import by.epam.jonline_introduction.part06.task02.controller.impl.FindNoteByDateCommand;
import by.epam.jonline_introduction.part06.task02.controller.impl.FindNoteByEmailCommand;
import by.epam.jonline_introduction.part06.task02.controller.impl.FindNoteBySubjectAndDateAfterCommand;
import by.epam.jonline_introduction.part06.task02.controller.impl.FindNoteBySubjectCommand;
import by.epam.jonline_introduction.part06.task02.controller.impl.FindNoteByWordInMessageCommand;
import by.epam.jonline_introduction.part06.task02.controller.impl.RemoveNoteCommand;
import by.epam.jonline_introduction.part06.task02.controller.impl.ShowAllNotesCommand;

public class CommandProvider {

	private Map<String, Command> commandMap = new HashMap<String, Command>();

	public CommandProvider() {
		commandMap.put("1", new AddNoteCommand());
		commandMap.put("2", new RemoveNoteCommand());
		commandMap.put("3", new ShowAllNotesCommand());
		commandMap.put("4", new FindNoteByDateCommand());
		commandMap.put("5", new FindNoteBySubjectCommand());
		commandMap.put("6", new FindNoteByEmailCommand());
		commandMap.put("7", new FindNoteBySubjectAndDateAfterCommand());
		commandMap.put("8", new FindNoteByWordInMessageCommand());
		commandMap.put("9", new ExitCommand());
	}

	public Command getCommand(String commandName) {
		Command command;
		command = commandMap.get(commandName);
		return command;
	}
}
