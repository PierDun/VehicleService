<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>GetByID</title>
</head>
<body>
<jsp:include page="menu.jsp">
  <jsp:param name="id" value="active" />
</jsp:include>
<form align="center" style="margin-top: 20px" name="getVehicleByID">
  <caption><h2>Get vehicle by id</h2></caption>
  <input class="form-control mt-3" type="text" name="id" id = "id" value="0" style="width: 30%; margin: 0 auto;"/>
    <div class="mx-auto" style="color: red">
    </div>
  <input type="button" name="get" class="btn btn-primary mx-auto mt-3" value="find" onclick="sendGetRequest()"/>
</form>
  <div align="center" class="mx-5">
    <caption><h2 id = "info"></h2></caption>
    <table border="1" cellpadding="13" class="table" id = "table">
      <thead class="thead-dark">
      <tr>
        <th>ID</th>
        <th>Name</th>
        <th>X</th>
        <th>Y</th>
        <th>Creation Date</th>
        <th>Engine Power</th>
        <th>Number of Wheels</th>
        <th>Type</th>
        <th>Fuel Type</th>
      </tr>
      </thead>
    </table>
  </div>
<script>
  <%@include file="js/getByID.js"%>
</script>
</body>
</html>