package by.epam.totalizator.command;

import java.util.HashMap;
import java.util.Map;

import by.epam.totalizator.command.impl.AllowBetCommand;
import by.epam.totalizator.command.impl.ChangeLocaleCommand;
import by.epam.totalizator.command.impl.ContactsCommand;
import by.epam.totalizator.command.impl.LoginCommand;
import by.epam.totalizator.command.impl.LogoutCommand;
import by.epam.totalizator.command.impl.MakeDepositCommand;
import by.epam.totalizator.command.impl.RegisterUserCommand;
import by.epam.totalizator.command.impl.RegistrationCommand;
import by.epam.totalizator.command.impl.RemoveUserCommand;
import by.epam.totalizator.command.impl.ShowBetHistoryCommand;
import by.epam.totalizator.command.impl.ShowDepositCommand;
import by.epam.totalizator.command.impl.ShowLineCommand;
import by.epam.totalizator.command.impl.ShowMainPage;
import by.epam.totalizator.command.impl.ShowUserListCommand;
import by.epam.totalizator.command.impl.UnknownCommand;

public class CommandHelper {

	private Map<CommandName, Command> commands = new HashMap<>();
	
	public CommandHelper() {
		commands.put(CommandName.ALLOW_BET, new AllowBetCommand());
		commands.put(CommandName.CHANGE_LOCALE, new ChangeLocaleCommand());
		commands.put(CommandName.CONTACTS, new ContactsCommand());
		commands.put(CommandName.HOME, new ShowMainPage());
		commands.put(CommandName.LOGIN, new LoginCommand());
		commands.put(CommandName.LOGOUT, new LogoutCommand());
		commands.put(CommandName.MAKE_DEPOSIT, new MakeDepositCommand());
		commands.put(CommandName.REGISTER_USER, new RegisterUserCommand());
		commands.put(CommandName.REGISTRATION, new RegistrationCommand());
		commands.put(CommandName.REMOVE_USER, new RemoveUserCommand());
		commands.put(CommandName.SHOW_BET_HISTORY, new ShowBetHistoryCommand());
		commands.put(CommandName.SHOW_DEPOSIT, new ShowDepositCommand());
		commands.put(CommandName.SHOW_LINE, new ShowLineCommand());
		commands.put(CommandName.SHOW_USER_LIST, new ShowUserListCommand());
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
