<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form name="LoginForm" method="POST" action="controller">
    <input type="hidden" name="command" value="Login"/>
    <br/>Login:<br/>
    <input type="text" name="Login" value="" placeholder="login"/><br/>
    <br/>Password:<br/>
    <input type="password" name="password" value="" placeholder="password"/><br/>
    <br/>
    <input type="submit" value="Sign in"/>
</form><hr/>
</body>
</html>
