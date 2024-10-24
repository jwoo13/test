<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login</title>
</head>
<body>


<form action="${pageContext.request.contextPath}/login" method="post">
    <label for="mid">아이디: </label>
    <input type="text" id="mid" name="mid" required><br>

    <label for="mpw">비밀번호: </label>
    <input type="password" id="mpw" name="mpw" required><br>

    <label>
        <input type="checkbox" name="auto"> 자동 로그인
    </label><br>

    <button type="submit">LOGIN</button>
</form>


<div>
    <button onclick="location.href='${pageContext.request.contextPath}/signup'">
        회원가입
    </button>
</div>
</body>
</html>
