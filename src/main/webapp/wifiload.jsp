<%@ page import="API.ApiExplorer" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%
    ApiExplorer apiExplorer = new ApiExplorer();
    int totalCount = apiExplorer.saveAllWifiListFromPublicData();
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>와이파이 정보 구하기</title>
</head>
<body>

    <h1><%=totalCount%>개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
    <a href="index.jsp">홈으로 가기</a>
</body>
</html>
