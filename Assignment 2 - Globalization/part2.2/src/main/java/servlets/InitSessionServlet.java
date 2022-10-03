package servlets;


import java.io.IOException;
import java.util.Locale;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.jsp.jstl.core.Config;

@WebServlet("/home")
public final class InitSessionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public InitSessionServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("locale")) {
                    // Set locale from cookie
                    Config.set(req.getSession(), Config.FMT_LOCALE, cookie.getValue());
                }
            }
        } else {
            Locale locale = req.getLocale();
            
            Config.set(req.getSession(), Config.FMT_LOCALE, locale.getLanguage());

            Cookie localeCookie = new Cookie("locale", locale.getLanguage());
            
            localeCookie.setMaxAge(365 * 24 * 60 * 60);
            
            resp.addCookie(localeCookie);
        }
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
