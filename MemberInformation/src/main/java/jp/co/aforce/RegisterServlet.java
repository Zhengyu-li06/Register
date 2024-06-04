package jp.co.aforce;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.aforce.bean.Client;
import jp.co.aforce.dao.ClientDAO;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        
        String identity = request.getParameter("identity");
        String fullname1 = request.getParameter("fullname1");
        String fullname2 = request.getParameter("fullname2");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        String email = request.getParameter("email");
        String confirmEmail = request.getParameter("confirm_email");
        String birthday = request.getParameter("birthday");
        String phone = request.getParameter("phone");
        String fax = request.getParameter("fax");
        
        if (identity == null || identity.isEmpty() || 
            password == null || password.isEmpty() || 
            fullname1 == null || fullname1.isEmpty() ||
            fullname2 == null || fullname2.isEmpty() ||
            email == null || email.isEmpty() ||
            confirmEmail == null || confirmEmail.isEmpty() ||
            birthday == null || birthday.isEmpty() ||
            phone == null || phone.isEmpty() ||
            fax == null || fax.isEmpty()) {
            session.setAttribute("error", "全てのフィールドを入力してください");
            response.sendRedirect("views/register.jsp");
            return;
        }
        
        if (!password.equals(confirmPassword)) {
            session.setAttribute("passwordMismatchError", "パスワードが一致しません");
            session.removeAttribute("emailMismatchError"); // 移除可能存在的其他错误消息
            session.removeAttribute("identityExistsError"); // 移除可能存在的其他错误消息
            response.sendRedirect("views/register.jsp");
            return;
        } else {
            session.removeAttribute("passwordMismatchError"); 
        }

        if (!email.equals(confirmEmail)) {
            session.setAttribute("emailMismatchError", "メールアドレスが一致しません");
            session.removeAttribute("passwordMismatchError"); // 移除可能存在的其他错误消息
            session.removeAttribute("identityExistsError"); // 移除可能存在的其他错误消息
            response.sendRedirect("views/register.jsp");
            return;
        } else {
            session.removeAttribute("emailMismatchError"); 
        }

        ClientDAO dao = new ClientDAO();

        try {
            if (dao.checkExistence(identity)) {
                session.setAttribute("identityExistsError", "このログイン名は既に使用されています");
                session.removeAttribute("passwordMismatchError"); // 移除可能存在的其他错误消息
                session.removeAttribute("emailMismatchError"); // 移除可能存在的其他错误消息
                response.sendRedirect("views/register.jsp");
                return;
            } else {
                session.removeAttribute("identityExistsError"); 
            }


            Client newClient = new Client();
            newClient.setIdentity(identity);
            newClient.setFullname1(fullname1);
            newClient.setFullname2(fullname2);
            newClient.setPassword(password);
            newClient.setEmail(email);
            newClient.setBirthday(birthday);
            newClient.setPhone(phone);
            newClient.setFax(fax);

            dao.create(newClient);

            session.setAttribute("client", newClient);
            response.sendRedirect("views/register-success.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "サーバーエラーが発生しました。");
            response.sendRedirect("views/register-error.jsp");
        }
    }
}
