<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Vote Details</title>
</head>
<body>
<h1>${vote.title}</h1>

<h3>Options</h3>

<ul>
    <c:forEach items="${vote.options}" var="option">
        <li>${option}</li>
    </c:forEach>
</ul>

<a href="${pageContext.request.contextPath}/Vote/list">[목록으로]</a>
</body>
</html>