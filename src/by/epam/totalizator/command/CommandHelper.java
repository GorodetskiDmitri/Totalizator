package by.epam.totalizator.command;

import java.util.HashMap;
import java.util.Map;

import by.epam.totalizator.command.impl.ChangeLocaleCommand;
import by.epam.totalizator.command.impl.ContactsCommand;
import by.epam.totalizator.command.impl.LoginCommand;
import by.epam.totalizator.command.impl.LogoutCommand;
import by.epam.totalizator.command.impl.RegistrationCommand;
import by.epam.totalizator.command.impl.ShowMainPage;
import by.epam.totalizator.command.impl.UnknownCommand;

public class CommandHelper {

	private Map<CommandName, Command> commands = new HashMap<>();
	
	public CommandHelper() {
		commands.put(CommandName.CHANGE_LOCALE, new ChangeLocaleCommand());
		commands.put(CommandName.CONTACTS, new ContactsCommand());
		commands.put(CommandName.HOME, new ShowMainPage());
		commands.put(CommandName.LOGIN, new LoginCommand());
		commands.put(CommandName.LOGOUT, new LogoutCommand());
		commands.put(CommandName.REGISTRATION, new RegistrationCommand());
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
