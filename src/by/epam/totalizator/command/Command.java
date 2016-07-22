package by.epam.totalizator.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.totalizator.command.exception.CommandException;

public interface Command {

	void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;
}
