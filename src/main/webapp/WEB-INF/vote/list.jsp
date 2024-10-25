<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="utf-8">
    <title>list 페이지</title>
</head>
<body>
<h1>투표 리스트</h1>



<form action="${pageContext.request.contextPath}/Vote/search" method="get">
    <input type="text" name="keyword" placeholder="검색어 입력" required>
    <button type="submit">검색</button>
</form>

<ul>
    <c:forEach items="${VoteList}" var="vote">
        <li>
            <hr>
            <h2>
                <a href="${pageContext.request.contextPath}/Vote/read?id=${vote.id}">
                        ${vote.title}
                </a>
            </h2>
            <form action="${pageContext.request.contextPath}/Vote/remove" method="post">
                <input type="hidden" name="id" value="${vote.id}">
                <button type="submit">삭제</button>
            </form>
        </li>
    </c:forEach>
</ul>



<a href="${pageContext.request.contextPath}/Vote/create">투표작성</a>
<br>

<form action="${pageContext.request.contextPath}/logout" method="post">
    <button type="submit">Logout</button>
</form>



</body>
</html>