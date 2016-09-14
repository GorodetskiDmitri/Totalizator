package by.epam.totalizator.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class OutcomeValue extends TagSupport {

	private static final long serialVersionUID = 1L;
	
	private String outcome;
	private String value;
	
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public int doStartTag() throws JspException {
		try {
			pageContext.getOut().write("<c:if test=\"${bet.outcome.equals(\"" + outcome + "\")}\">"
										+ "<c:out value=\"" + value + "\" />"
										+ "</c:if>");
		} catch (IOException e) {
			throw new JspException(e.getMessage());
		}
		return SKIP_BODY;
	}
}
