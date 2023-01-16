<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Vehicles</title>
  <script type="text/javascript">
    <%@include file="js/add.js"%>
  </script>
</head>
<body>
<jsp:include page="menu.jsp">
  <jsp:param name="new" value="active" />
</jsp:include>
<div class="vehicle-form">
  <caption>
    <h2>
        Add New Vehicle
    </h2>
  </caption>
  <div class="error-msg" style="white-space: pre-line">
  </div>
  <form method="post" align="center" name="addVehicleForm">
    <p>Name:
      <input type="text" name="name" class="form-control"/> </p>
    <p>X:
      <input type="text" name="x" class="form-control"/></p>
    <p>Y:
      <input type="text" name="y" class="form-control"/></p>
    <p>Engine Power:
      <input type="text" name="enginePower" class="form-control"/></p>
    <p>Number of Wheels:
      <input type="text" name="numberOfWheels" class="form-control"/></p>
    <p>Vehicle Type:
      <select name="type" class="form-control">
        <option value="PLANE" selected>PLANE</option>
        <option value="BOAT">BOAT</option>
        <option value="SHIP">SHIP</option>
        <option value="MOTORCYCLE">MOTORCYCLE</option>
        <option value="CHOPPER">CHOPPER</option>
      </select></p>
    <p>Fuel Type:
      <select name="fuelType" class="form-control">
        <option value="ALCOHOL" selected>ALCOHOL</option>
        <option value="MANPOWER">MANPOWER</option>
        <option value="PLASMA">PLASMA</option>
        <option value="ANTIMATTER">ANTIMATTER</option>
      </select></p>
    <input type="button" name="Create" class="btn btn-primary mx-auto mt-3" value="Create" onclick="addVehicle()"/>
  </form>
</div>
</body>
</html>