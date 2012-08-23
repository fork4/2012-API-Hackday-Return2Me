package com.fork4.return2me;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fork4.return2me.beans.Extension;
import com.fork4.return2me.twilio.IvrResponse;

/**
 * Servlet implementation class FetchNumber
 */
public class FetchNumber extends HttpServlet {
	private static final String NOT_FOUND_MSG = "Extension not found.";
	
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public FetchNumber() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processCall(request.getParameter("Digits"), response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processCall(request.getParameter("Digits"), response);
	}
	
	protected void processCall(String extension, HttpServletResponse response) throws IOException {
		response.setContentType("text/xml");
		String number = fetchNumber(extension);
		String action;
		
		if(!number.equals(NOT_FOUND_MSG)) {
			action = IvrResponse.callRedirect(number);
		} else {
			action = IvrResponse.badExtension();
		}

		response.getWriter().write(action);
		response.getWriter().flush();
	}
	
	protected String fetchNumber(String extension) {
		String number = NOT_FOUND_MSG;
		System.out.println("Extension: " + extension);
		if(extension != null) {
			HashMap<String, Extension>exts = getExtensions();
			
			if(exts.containsKey(extension)) {
				Extension ext = exts.get(extension);
				System.out.println("Send caller to " + ext.getRepPhone());
				
				number = ext.getRepPhone();
				if(ext.isOneTimeUse()) {
					exts.remove(extension);
				}
			
				this.getServletContext().setAttribute("extensions", exts);
			} 
		}
		
		return number;
	}
	
	@SuppressWarnings("unchecked")
	protected HashMap<String, Extension> getExtensions() {
		if(this.getServletContext().getAttribute("extensions") != null) {
			return (HashMap<String, Extension>)this.getServletContext().getAttribute("extensions");
		}
		
		return new HashMap<String, Extension>();
	}


}
