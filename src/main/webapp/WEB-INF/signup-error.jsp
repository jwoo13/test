<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SignUp Error</title>
</head>
<body>

<h1>회원가입 에러</h1>
<p>이미 존재하는 아이디입니다. 다른 아이디를 사용해 주세요.</p>

<button onclick="location.href='${pageContext.request.contextPath}/signup'">
    회원가입으로 돌아가기
</button>

<button onclick="location.href='${pageContext.request.contextPath}/login'">
    로그인하기
</button>

</body>
</html>
