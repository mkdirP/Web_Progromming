<%@ page import="Lab_2.Point" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collections" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="pointsBean" class="Lab_2.PointsTableBean" scope="session"/>
<html>
<head>
    <title>results</title>
    <link rel="stylesheet" href="css/style.css">
    <meta charset="utf-8">
</head>
<body>
<table class="results block centered">
    <tr> <th>No</th> <th>X</th> <th>Y</th> <th>R</th> <th><b>Результат</b></th> </tr>
    <%
        List<Point> list = pointsBean.getPoints();
        for (Point point : list) {
    %>
    <tr>
        <td><%=point.getN() %></td>
        <td><%=point.getX() %></td>
        <td><%=point.getY() %></td>
        <td><%=point.getR()%></td>
        <td><%=point.isHit() ? "True" : "False" %></td>

        <% if (list.get(0).getR() == point.getR()) { %>
        <script>
            parent.markPoint(<%=point.getX() %>, <%=point.getY() %>, <%=point.getR() %>, <%=point.isHit() %>)
        </script>
        <% } %>
    </tr>
    <%}%>
</table>
</body>
</html>
