package no.hvl.dat152.obl3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat152.obl3.database.AppUser;
import no.hvl.dat152.obl3.database.AppUserDAO;
import no.hvl.dat152.obl3.util.Crypto;
import no.hvl.dat152.obl3.util.Role;
import no.hvl.dat152.obl3.util.ServerConfig;
import no.hvl.dat152.obl3.util.Validator;

@WebServlet("/newuser")
public class NewUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("dictconfig", ServerConfig.DEFAULT_DICT_URL);
		request.getRequestDispatcher("newuser.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		doAction(request,response);

		boolean successfulRegistration = false;

		String username = Validator.validString(request
				.getParameter("username"));
		String password = Validator.validString(request
				.getParameter("password"));
		String confirmedPassword = Validator.validString(request
				.getParameter("confirm_password"));
		String firstName = Validator.validString(request
				.getParameter("first_name"));
		String lastName = Validator.validString(request
				.getParameter("last_name"));
		String mobilePhone = Validator.validString(request
				.getParameter("mobile_phone"));
		String preferredDict = Validator.validString(request
				.getParameter("dicturl"));

		AppUser user = null;
		if (password.equals(confirmedPassword)) {

			AppUserDAO userDAO = new AppUserDAO();

			user = new AppUser(username, Crypto.generateMD5Hash(password),
					firstName, lastName, mobilePhone, Role.USER.toString(),
					Crypto.generateRandomCryptoCode());						

			successfulRegistration = userDAO.saveUser(user);
		}

		if (successfulRegistration) {
			request.getSession().setAttribute("user", user);
			Cookie dicturlCookie = new Cookie("dicturl", preferredDict);
			dicturlCookie.setMaxAge(60*10);
			response.addCookie(dicturlCookie);

			response.sendRedirect("searchpage");

		} else {
			request.setAttribute("message", "Registration failed!");
			request.getRequestDispatcher("newuser.jsp").forward(request,
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

}
