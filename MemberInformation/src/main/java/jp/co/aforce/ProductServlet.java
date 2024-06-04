package jp.co.aforce;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.aforce.bean.Product;
import jp.co.aforce.dao.ProductDAO;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String keyword = request.getParameter("keyword");
        
        if (keyword == null) {
            keyword = "";
        }
        
        ProductDAO dao = new ProductDAO();
        List<Product> list = null;
		try {
			list = dao.search(keyword);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
        
        session.setAttribute("list", list);
        
        request.getRequestDispatcher("views/product.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}
