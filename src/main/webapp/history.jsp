<%@ page import="API.ApiExplorer" %>
<%@ page import="java.util.List" %>
<%@ page import="DTO.HistoryDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <style>
        table {
            width : 100%;
        }
        table, th, td {
            border: solid 1px;
        }
        td {
            text-align: center;
        }
        thead {
            background-color: #00AA6F;
            color: white;
        }
        thead > tr {
            height: 3em;
        }
    </style>
</head>
<body>
<%
    ApiExplorer apiExplorer = new ApiExplorer();
    List<HistoryDTO> histories = apiExplorer.getAllHistoryDTO();
%>
    <h1>위치 히스토리 목록</h1>
    <a href="index.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="wifiload.jsp">Open API 정보 가져오기</a>
    <br>
    <table id = "history">
        <thead>
            <tr>
                <th>ID</th>
                <th>X좌표</th>
                <th>Y좌표</th>
                <th>조회일자</th>
                <th>비고</th>
            </tr>
        </thead>
        <tbody>
            <% for (int i = histories.size() - 1; i >= 0; i--) {
                HistoryDTO history = histories.get(i);
            %>
        <tr>
            <td><%=history.getId()%></td>
            <td><%=history.getLatitude()%></td>
            <td><%=history.getLongitude()%></td>
            <td><%=history.getLookupDate()%></td>
            <td><button id=<%=history.getId() %>>삭제</button></td>
        </tr>
        <% } %>
        </tbody>
    </table>
</body>
</html>
