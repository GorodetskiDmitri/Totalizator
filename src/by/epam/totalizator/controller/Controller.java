package by.epam.totalizator.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.totalizator.command.Command;
import by.epam.totalizator.command.CommandHelper;
import by.epam.totalizator.command.exception.CommandException;

/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private CommandHelper commandHelper = CommandHelper.getInstance();

    public Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commandName;
		Command command;
		String page = null;
		try {
			commandName = request.getParameter(RequestParameterName.COMMAND);
			command = commandHelper.getCommand(commandName);
			System.out.println("commandName=" + commandName);
			command.execute(request, response);
		} catch (CommandException e) {
			request.setAttribute(RequestParameterName.ERROR_MESSAGE, e.getMessage());
			page = PageName.ERROR_PAGE;
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			if (dispatcher != null) {
				dispatcher.forward(request, response);
			} 
		}
	}

}