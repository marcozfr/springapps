package spittr.web.servlet;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;

import javax.el.ELContext;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.el.ExpressionEvaluator;
import javax.servlet.jsp.el.VariableResolver;
import javax.sound.midi.SysexMessage;

import org.springframework.session.web.http.HttpSessionManager;

@WebServlet("/spitterinfo")
public class SpitterServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		System.out.println("sess:" +Collections.list(session.getAttributeNames()));
		
		System.out.println("req: "+Collections.list(req.getAttributeNames()));
		
		HttpSessionManager sessionManager=(HttpSessionManager)req.getAttribute(
		        "org.springframework.session.web.http.HttpSessionManager");
		
		System.out.println("current alias: " + sessionManager.getCurrentSessionAlias(req));
		
		System.out.println("current session ids: " + sessionManager.getSessionIds(req));
		
	}
	
}
