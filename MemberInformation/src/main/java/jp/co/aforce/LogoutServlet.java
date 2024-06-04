package jp.co.aforce;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        
        if (session.getAttribute("client") != null) { 
            session.invalidate();
            response.sendRedirect("/views/logout-out.jsp");
        } else {
            response.sendRedirect("/views/logout-error.jsp");
        }
    }
}
