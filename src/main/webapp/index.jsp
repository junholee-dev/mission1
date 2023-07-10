<%@ page import="API.ApiExplorer" %>
<%@ page import="DTO.WifiDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="util.MathUtil" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>와이파이 정보 구하기</title>
  <style>
    table {
      width : 100%
    }
    table, th, td {
      border: solid 1px;
    }
    thead {
      background-color: #00AA6F;
      color: white;
    }
  </style>
</head>
<body>
<%
  ApiExplorer apiExplorer = null;
  List<WifiDTO> nearbyWifi = new ArrayList<WifiDTO>();
  Double curLatitude = null;
  Double curLongitude = null;
  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    if (request.getParameter("latitude") != null && request.getParameter("longitude") != null) {
        curLatitude = Double.parseDouble(request.getParameter("latitude"));
        curLongitude = Double.parseDouble(request.getParameter("longitude"));
        apiExplorer = new ApiExplorer();
        nearbyWifi = apiExplorer.getNearbyWifiList(curLatitude, curLongitude);
    }
%>
<h1>와이파이 정보 구하기</h1>

<a href = "index.jsp">홈</a>|
<a href = "history.jsp">위치 히스토리 목록</a>|
<a href = "wifiload.jsp">Open API 와이파이 정보 가져오기</a>

<br>
<form action="/">
  <label for="latitude">LAT:</label>
  <input type = "text" id="latitude" name="latitude" value="<%=curLatitude == null ? 0 : curLatitude %>" />
  <label for="longitude">LNT:</label>
  <input type="text" id="longitude" name="longitude" value="<%=curLongitude == null ? 0 : curLongitude %>" />
  <button type="button" id="position-btn">내 위치 가져오기</button>
  <button type="submit">근처 WIFI 정보 보기</button>
</form>


<table>
  <thead>
    <tr>
      <th>거리(km)</th>
      <th>관리번호</th>
      <th>자치구</th>
      <th>와이파이명</th>
      <th>도로명주소</th>
      <th>상세주소</th>
      <th>설치위치(층)</th>
      <th>설치유형</th>
      <th>설치기관</th>
      <th>서비스구분</th>
      <th>망종류</th>
      <th>설치년도</th>
      <th>실내외구분</th>
      <th>WIFI접속환경</th>
      <th>X좌표</th>
      <th>Y좌표</th>
      <th>작업일자</th>

    </tr>
  </thead>
  <tbody>
    <% for (WifiDTO wifi : nearbyWifi) { %>
  <tr>
      <td><%=String.format("%.4f", MathUtil.calculateDistance(curLatitude, curLongitude, wifi.getLatitude(), wifi.getLongitude()))%></td>
      <td><%=wifi.getManagement_number()%></td>
     <td><%=wifi.getDistrict() %></td>
     <td><%=wifi.getName() %></td>
      <td><%=wifi.getAddress1() %></td>
      <td><%=wifi.getAddress2() %></td>
      <td><%=wifi.getInstalled_floor() %></td>
      <td><%=wifi.getInstall_type() %></td>
      <td><%=wifi.getInstall_agency() %></td>
      <td><%=wifi.getService_classification() %></td>
      <td><%=wifi.getMesh_type() %></td>
      <td><%=wifi.getInstall_year() %></td>
      <td><%=wifi.getIn_out_door() %></td>
      <td><%=wifi.getConnection_environment() %></td>
      <td><%=wifi.getLatitude() %></td>
      <td><%=wifi.getLongitude() %></td>
      <td><%=simpleDateFormat.format(wifi.getWorkDateStr()) %></td>
  </tr>
  <% } %>
  </tbody>
</table>
</body>
<script>
    const positionBtn = document.querySelector('#position-btn');
    positionBtn.addEventListener('click', event => {
      const latitudeInput = document.querySelector('#latitude');
      const longitudeInput = document.querySelector('#longitude');
      navigator.geolocation.getCurrentPosition((position) => {
        latitudeInput.value = position.coords.latitude;
        longitudeInput.value = position.coords.longitude;
      });
    });
</script>
</html>