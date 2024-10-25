<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <title>투표 결과</title>
</head>
<body>
<h1>투표 결과</h1>

<div>
  <c:forEach items="${optionList}" var="option">
      <strong>항목:</strong> ${option.optionText}
      <br>
      <strong>득표수:</strong> ${option.voteCount}
    <br>
    ----------------------------------------------- <br>
  </c:forEach>
</div>


<a href="${pageContext.request.contextPath}/Vote/list">목록으로</a>
</body>
</html>
