package com.fork4.return2me;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fork4.return2me.beans.Extension;
import com.fork4.return2me.twilio.SendExtension;

/**
 * Servlet implementation class GenerateExtension
 */
public class GenerateExtension extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerateExtension() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		generateExtension(request.getParameter("repPhone"), request.getParameter("custPhone"), 
				"true".equals(request.getParameter("oneTimeUse")));
		
		response.sendRedirect("index.jsp" );
	}
	
	protected void generateExtension(String repPhone, String customerPhone, boolean oneTimeUse) {
		System.out.println("Generating extension for " + repPhone);
		if(repPhone != null) {
		
			HashMap<String, Extension>exts = getExtensions();
			
			SecureRandom rand = new SecureRandom();
			
			String extension = String.valueOf(rand.nextInt(99999));
			
			// Add the extension
			Extension ext = new Extension(repPhone, extension, customerPhone, oneTimeUse);
			
			// Send it to the customer
			if(ext.hasCustomerPhone()) {
				SendExtension.send(extension, customerPhone);
			}

			exts.put(extension, ext);
			this.getServletContext().setAttribute("extensions", exts);
		}
		
	}
	
	
	protected HashMap<String, Extension> getExtensions() {
		if(this.getServletContext().getAttribute("extensions") != null) {
			return (HashMap<String, Extension>)this.getServletContext().getAttribute("extensions");
		}
		
		return new HashMap<String, Extension>();
	}
	


}
