package no.hvl.dat152.obl3.controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat152.obl3.database.AppUser;
import no.hvl.dat152.obl3.database.AppUserDAO;
import no.hvl.dat152.obl3.util.Validator;

@WebServlet("/updatepassword")
public class UpdatePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// check that the user has a valid session
		if(RequestHelper.isLoggedIn(request))
			request.getRequestDispatcher("updatepassword.jsp").forward(request, response);
		else {
			request.setAttribute("message", "Session has expired. Login again!");
			request.getRequestDispatcher("login").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doAction(request,response);
		request.removeAttribute("message");

		boolean successfulPasswordUpdate = false;
		
		String passwordnew = Validator.validString(request
				.getParameter("passwordnew"));
		String confirmedPasswordnew = Validator.validString(request
				.getParameter("confirm_passwordnew"));
		
		
		if (RequestHelper.isLoggedIn(request)) {
			
			AppUser user = (AppUser) request.getSession().getAttribute("user");
			
			AppUserDAO userDAO = new AppUserDAO();
			
			if (passwordnew.equals(confirmedPasswordnew) && PassordValidering(passwordnew)){
				
				successfulPasswordUpdate = userDAO.updateUserPassword(user.getUsername(), passwordnew);
				
				if (successfulPasswordUpdate) {
					request.getSession().invalidate(); // invalidate current session and force user to login again
					request.setAttribute("message", "Password successfully updated. Please login again!");
					response.sendRedirect("login");

				} else {
					request.setAttribute("message", "Password update failed!");
					request.getRequestDispatcher("updatepassword.jsp").forward(request,
							response);
				}
			} else {
				request.setAttribute("message", "Password fields do not match. Try again!");
				request.getRequestDispatcher("updatepassword.jsp").forward(request,
						response);
			}
			
		} else {
			request.getSession().invalidate();
			request.getRequestDispatcher("index.html").forward(request,
					response);
		}

	}
	
	public void doAction(HttpServletRequest request, HttpServletResponse response) {
		// get the CSRF cookie
		String csrfCookie = null;
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equals("csrfToken")) {
				csrfCookie = cookie.getValue();
			}
		}
		// get the CSRF form field
		String csrfField = request.getParameter("csrfToken");

		// validate CSRF
		if (csrfCookie == null || csrfField == null || !csrfCookie.equals(csrfField)) {
			try {
				response.sendError(401);
			} catch (IOException e) {
				// ...
			}
			return;
		}
		// ...
	}
	
	public static boolean PassordValidering(String password) {
		String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher passwordMatch = pattern.matcher(password);
		return passwordMatch.matches();
	}

}
