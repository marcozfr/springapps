package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.BlockingQueue;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.example.model.Person;

/**
 * Servlet implementation class ProcessorServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "*.html" })
public class ProcessorServlet extends HttpServlet {
	
	public static final String NASHORN_COMPAT_SCRIPT = "if (typeof importClass != \"function\") { load(\"nashorn:mozilla_compat.js\"); }";
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ProcessorServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String requestUrl = request.getRequestURI().substring(request.getContextPath().length());
		InputStream in = getServletContext().getResourceAsStream(requestUrl);
		Document document = Jsoup.parse(in, "utf-8", "");
		Element script = document.select("script[type='server/javascript']").first();
		script.text();
		
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
		try {
			engine.eval(script.data());
			Person p = (Person)engine.eval("person");
			System.out.println(p);
		} catch (ScriptException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
