package servlets;

import java.io.IOException;

import database.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.jsp.jstl.core.Config;
import webshop.Cart;
import webshop.Converter;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CartServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
    	String locale = (String) Config.get(req.getSession(), Config.FMT_LOCALE); 
    	DAO productDAO = new DAO();
    	Converter conv = new Converter();
    	
    	Cart cart = new Cart();
    	
    	conv.convertWEB(productDAO, cart.getProducts(), locale);
		
    	req.setAttribute("cart", cart);
    	
    	
        req.getRequestDispatcher("/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
