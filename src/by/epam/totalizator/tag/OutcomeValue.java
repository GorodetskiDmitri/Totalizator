package by.epam.totalizator.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class OutcomeValue extends TagSupport {

	private static final long serialVersionUID = 1L;
	
	private String outcome;
	private String locale;
	private String print;
	
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
	
	public void setLocale(String locale) {
		this.locale = locale;
	}
	
	@Override
	public int doStartTag() throws JspException {
		try {
			switch(outcome) {
			case "1":
				if (locale.equalsIgnoreCase("ru")) {
					print = "Ï1";
				} else {
					print = "Win 1";
				}
				break;
			case "2":
				if (locale.equalsIgnoreCase("ru")) {
					print = "Õ";
				} else {
					print = "Draw";
				}
				break;
			case "3":
				if (locale.equalsIgnoreCase("ru")) {
					print = "Ï2";
				} else {
					print = "Win 2";
				}
				break;
			default:
				print = "";
			}
			pageContext.getOut().write(print);
		} catch (IOException e) {
			throw new JspException(e.getMessage());
		}
		return SKIP_BODY;
	}
}
