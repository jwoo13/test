<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>투표하기</title>
    <script>
        function validateForm() {
            const selectedOption = document.querySelector('input[name="selectedOption"]:checked');
            if (!selectedOption) {
                alert("투표 해주세요");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<h1>${vote.title}</h1>

<form action="${pageContext.request.contextPath}/Vote/read?id=${vote.id}" method="post" onsubmit="return validateForm()">
    <h3>투표 항목</h3>
    <ul>
        <c:forEach items="${vote.options}" var="option">
            <li>
                <label>
                    <input type="radio" name="selectedOption" value="${option}">
                        ${option}
                </label>
            </li>
        </c:forEach>
    </ul>

    <button type="submit">완료</button>
</form>

<a href="${pageContext.request.contextPath}/Vote/list">목록으로</a>
</body>
</html>