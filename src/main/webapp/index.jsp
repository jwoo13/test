<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    // /login 경로로 리다이렉트
    response.sendRedirect(request.getContextPath() + "/login");
%>