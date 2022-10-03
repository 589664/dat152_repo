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

@WebServlet("/products")
public final class ProductsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private DAO database = new DAO();
	private Converter conv = new Converter();
	private Cart cart = new Cart(0, conv.convert(database, "no_NO"));

    public ProductsServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
    	String locale = (String) Config.get(req.getSession(), Config.FMT_LOCALE); 
    	
    	req.setAttribute("products", conv.convert(database, locale));
    	
    	//forward
    	req.getRequestDispatcher("products.jsp").forward(req, resp);
    	
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
    	String itemToAdd = req.getParameter("itemid");
		
		cart.add(Integer.valueOf(itemToAdd));
		
		Cart.staticItems = cart.getProducts();
		
		doGet(req, resp);
    }

}
