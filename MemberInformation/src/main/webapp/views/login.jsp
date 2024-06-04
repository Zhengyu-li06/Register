<%@page contentType="text/html; charset=UTF-8" %>
<html>
<head>
<title>Login</title>
<link rel="stylesheet" type="text/css" href="../css/LoginandNavy.css">

</head>
<body>

<div class="navy">
    <nav>
            
        <ul class="display">
            <li><a href="main.html">TOP</a></li>
            
            <li id="information">
                <a>INFORMATION</a>
                <ul class="hidden">
                          <li><a href="#HOMEPAGE">HOMEPAGE</a></li>
                          <li><a href="#ABOUT US">ABOUT US</a></li>
                         
                      </ul>
            </li>
            <li><a href="cart.jsp">CART</a></li>
            <li><a href="news.jsp">NEWS</a></li>
            
        </ul>
        </nav>
        </div>
<container>
<form action="<%=request.getContextPath()%>/LoginServlet" method="post">
    <p>ログイン名<input type="text" name="identity" value="<%= session.getAttribute("identity") != null ? session.getAttribute("identity") : "" %>"></p>
    <p>パスワード<input type="password" name="password"></p>
    <input type="submit" value="ログイン">
</form>

<% 

String error = (String) session.getAttribute("error");
if (error != null) {
%>
<p><%= error %></p>
<%
}

session.removeAttribute("error");
%>
<p><a href="register.jsp">会員登録が済んでいない人はこちら</a></p>


</container>
</body>
</html>
