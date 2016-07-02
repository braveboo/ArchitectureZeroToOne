<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>没有权限</title>
    <style>.error{color:red;}</style>
</head>
<body>

<div class="error">您没有权限[${exception.message}]</div>
<input type="button" name="button1"
       id="button1" value="返回" onclick="history.go(-1)">
</body>
</html>