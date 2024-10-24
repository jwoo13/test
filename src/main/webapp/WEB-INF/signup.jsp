<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>

<h1>회원가입</h1>

<form action="${pageContext.request.contextPath}/signup" method="post">
    <div>
        <label for="mid">아이디:</label>
        <input type="text" id="mid" name="mid" placeholder="아이디 입력" required>
    </div>
    <div>
        <label for="mpw">비밀번호:</label>
        <input type="password" id="mpw" name="mpw" placeholder="비밀번호 입력" required>
    </div>
    <div>
        <button type="reset">초기화</button>
        <button type="submit">회원가입</button>
    </div>
</form>

</body>
</html>
