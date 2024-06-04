<%@page contentType="text/html; charset=UTF-8" %>
<html>
<head>
<title>Welcome~~~</title>
<link rel="stylesheet" type="text/css" href="../css/Login-out.css">
</head>
<body>
<meta http-equiv="refresh" content="10;url=homepage.jsp">

<script type="text/javascript">
    var countdown = 10;
    function updateCountdown() {
        if (countdown > 0) {
            document.getElementById('countdown').innerText = countdown + 'ホームページに移動します。';
            countdown--;
            setTimeout(updateCountdown, 1000);
        } else {
            window.location.href = 'homepage.jsp';
        }
    }
    window.onload = updateCountdown;
</script>

<div class="container">

<h2>こんにちは、${client.identity}さん。</h2>

<p id="countdown">60秒後ホームページに移動します。</p>

<p><a href="homepage.jsp">ホームページに</a></p>
</div>


</body>
</html>
