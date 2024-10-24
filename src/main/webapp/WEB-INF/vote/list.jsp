
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="utf-8">
    <title>list 페이지</title>
</head>
<body>
<h1>Vote List</h1>


<h3>* 새 글 작성 *</h3>
<form action="${pageContext.request.contextPath}/Vote/create" method="post">
    <div>
        <input type="text" name="title" placeholder="INSERT CONTENT" required>
    </div>
    <div>
        <button type="reset">RESET</button>
        <button type="submit">REGISTER</button>
    </div>
</form>

<%--<ul>--%>
<%--    <c:forEach items="${VoteList}" var="vote">--%>
<%--        <li>--%>
<%--            <hr>--%>
<%--            <h2>글 내용:<a href="/Vote/read?id=${vote.id}">클릭</a></h2>--%>
<%--            <br>--%>
<%--            <strong>- 내용:</strong> ${vote.title}--%>
<%--            <br>--%>

<%--            <form action="${pageContext.request.contextPath}/Vote/remove" method="post">--%>
<%--                <input type="hidden" name="id" value="${vote.id}">--%>
<%--                <button type="submit">게시물 삭제</button>--%>
<%--            </form>--%>

<%--            <a href="/Vote/modify?id=${vote.id}" class="button">게시물 수정</a>--%>

<%--            <br>--%>
<%--        </li>--%>
<%--    </c:forEach>--%>
<%--</ul>--%>




<!-- 검색 폼 -->
<form action="${pageContext.request.contextPath}/Vote/search" method="get">
    <input type="text" name="keyword" placeholder="검색어 입력" required>
    <button type="submit">검색</button>
</form>

<ul>
    <c:forEach items="${VoteList}" var="vote">
        <li>
            <hr>
            <h2><a href="/Vote/read?id=${vote.id}">${vote.title}</a></h2>
            <form action="${pageContext.request.contextPath}/Vote/remove" method="post">
                <input type="hidden" name="id" value="${vote.id}">
                <button type="submit">삭제</button>
            </form>
        </li>
    </c:forEach>
</ul>




</body>
</html>