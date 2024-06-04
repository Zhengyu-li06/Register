package jp.co.aforce;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.aforce.bean.Client;
import jp.co.aforce.dao.ClientDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        
        String identity = request.getParameter("identity");
        String password = request.getParameter("password");
        ClientDAO dao = new ClientDAO();
        Client client = null;
        try {
            client = dao.search(identity, password);
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "サーバーエラーが発生しました。");
            response.sendRedirect(request.getContextPath() + "/views/login-error.jsp");
            return;
        }
        
        if (client != null) { 
            session.setAttribute("client", client); 
            response.sendRedirect(request.getContextPath() + "/views/login-out.jsp");
        } else {
            session.setAttribute("error", "IDもしくはパスワードが違います");
            session.setAttribute("identity", identity); 
            response.sendRedirect(request.getContextPath() + "/views/login-error.jsp");
        }
    }
}
