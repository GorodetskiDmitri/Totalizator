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
	private static final String COMMAND_NAME = "command";
	private final CommandHelper commandHelper = new CommandHelper();

    public Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.getSession(true).setAttribute("locale", request.getParameter("locale"));
		//request.getRequestDispatcher("index.jsp").forward(request, response);
		String commandName;
		Command command;
		String page = null;
		try {
			commandName = request.getParameter(COMMAND_NAME);
			command = commandHelper.getCommand(commandName);
			System.out.println("commandName=" + commandName);
			command.execute(request, response);
		} catch (CommandException e) {
			page = PageName.ERROR_PAGE;
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			if (dispatcher != null) {
				dispatcher.forward(request, response);
			} else {
				
			}
		}
	}

}