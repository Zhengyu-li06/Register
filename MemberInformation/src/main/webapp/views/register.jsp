<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
<title>Register</title>
<link rel="stylesheet" type="text/css" href="../css/Style.css">

</head>
<body>

<container>

<h2 style="margin: 20px auto;
    text-align: center;
    background-color: #4CAF50;
    max-width: 52%;
    padding: 10px;
    color: white;
    border-radius: 6px;
    font-size:large;
    font-family:'Josefin Slab',serif;">
新規登録</h2>


<form action="<%=request.getContextPath()%>/RegisterServlet" method="post">


    <label for="identity">ログイン名：</label>
<input type="text" id="identity" name="identity" required><br><br>
<% 
    String identityExistsError = (String) session.getAttribute("identityExistsError");
    if(identityExistsError != null) { 
%>
    <span style="color: red;"><%= identityExistsError %></span><br><br>
<% 
    session.removeAttribute("identityExistsError"); 
} %>
    
    
    <label for="fullname1">名前：</label>
    <input type="text" id="fullname1" name="fullname1" required><br><br>
    
    
    
    <label for="fullname2">名前（カナ）：</label>
    <input type="text" id="fullname2" name="fullname2" required><br><br>
    
    
    
    <label for="password">パスワード入力：</label>
    <input type="password" id="password" name="password" required><br><br>
<% 
    String passwordMismatchError = (String) session.getAttribute("passwordMismatchError");
    if(passwordMismatchError != null) { 
%>
    <span style="color: red;"><%= passwordMismatchError %></span><br><br>
<% 
    session.removeAttribute("passwordMismatchError"); 
} %>



    <label for="confirm_password">パスワード確認：</label>
    <input type="password" id="confirm_password" name="confirm_password" required><br><br>
    
    
    
    <label for="email">メールアドレス：</label>
    <input type="email" id="email" name="email" required><br><br>
<% 
    String emailMismatchError = (String) session.getAttribute("emailMismatchError");
    if(emailMismatchError != null) { 
%>
    <span style="color: red;"><%= emailMismatchError %></span><br><br>
<% 
    session.removeAttribute("emailMismatchError"); 
} %>



    <label for="confirm_email">メールアドレス確認：</label>
    <input type="email" id="confirm_email" name="confirm_email" required><br><br>
    
    
    
    <label for="birthday">生年月日：</label>
    <input type="date" id="birthday" name="birthday" required><br><br>
    
    
    
    <label for="phone">電話番号：</label>
    <input type="tel" id="phone" name="phone" required><br><br>
    
    
    
    <label for="fax">FAX番号：</label>
    <input type="tel" id="fax" name="fax" required><br><br>
    
    
    
    <input type="submit" value="登録">
    
    
</form>

<p style="margin:10px;"><a href="login.jsp">既に登録済みの方はこちら</a></p>

</container>
</body>
</html>
