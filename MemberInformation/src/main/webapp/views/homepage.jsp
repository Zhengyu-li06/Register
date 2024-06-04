<%@page contentType="text/html; charset=UTF-8" %>
<html>
<head>
<title>Homepage</title>
<link rel="stylesheet" type="text/css" href="../css/Homepage.css?v=<%= System.currentTimeMillis() %>">

</head>
<body>
<div class="container">
    <div id="message" class="message" onclick="redirectToProduct()">
        何を買いたいですか？<br>
        こんにちは、${client.identity}さん。
    </div>
</div>
<footer>
    <p><a href="logout-in.jsp">ログアウトしますか？</a></p>
</footer>
<script>
    function redirectToProduct() {
        window.location.href = "product.jsp"; 
    }
</script>
</body>
</html>