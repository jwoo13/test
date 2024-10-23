<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/register" method="post">
    <div>
        <input type="text" name="title" placeholder="INSERT TITLE" required>
    </div>
    <div>
        <input type="date" name="dueDate" required>
    </div>
    <div>
        <button type="reset">RESET</button>
        <button type="submit">REGISTER</button>
    </div>
</form>
</body>
</html>