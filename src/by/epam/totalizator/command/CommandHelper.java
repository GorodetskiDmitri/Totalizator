package by.epam.totalizator.command;

import java.util.HashMap;
import java.util.Map;

import by.epam.totalizator.command.impl.ChangeLocaleCommand;
import by.epam.totalizator.command.impl.LoginCommand;
import by.epam.totalizator.command.impl.UnknownCommand;

public class CommandHelper {

	private Map<CommandName, Command> commands = new HashMap<>();
	
	public CommandHelper() {
		commands.put(CommandName.CHANGE_LOCALE, new ChangeLocaleCommand());
		commands.put(CommandName.LOGIN, new LoginCommand());
	}
	
	public Command getCommand(String commandName) {
		commandName = commandName.replace('-', '_').toUpperCase();
		Command command = null;
		CommandName key = null;
		key = CommandName.valueOf(commandName);
		command = commands.get(key);
		if (command == null) {
			command = new UnknownCommand();
		}
		return command;
	}
}
