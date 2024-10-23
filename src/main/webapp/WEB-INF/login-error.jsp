<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Error</title>
</head>
<body>

<h1>로그인 에러</h1>
<p>잘못된 아이디 또는 비밀번호입니다.</p>

<button onclick="location.href='${pageContext.request.contextPath}/login'">
    로그인으로 돌아가기
</button>

</body>
</html>
