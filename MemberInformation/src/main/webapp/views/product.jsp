<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ include file="menu.jsp" %>
<html>
<head>
<title>Shopping</title>
</head>
<body>
<p>検索キーワードを入力してください。</p>
<form action="/ProductServlet" method="post">
    <input type="text" name="keyword">
    <input type="submit" value="検索">
</form>

<hr>

<table style="border-collapse:separate; border-spacing:10px;">
    <c:forEach var="product" items="${list}">
        <tr>
            <td>商品${product.id}</td>
            <td><img src="img/${product.id}.jpg" height="100"></td>
            <td>${product.name}</td>
            <td>${product.price}円</td>
            <td><a href="CartAdd.action?id=${product.id}">カートに追加</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>