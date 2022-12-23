<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>СОА - 2-ая Лабораторная</title>
</head>
<style>
    <%@ include file="/css/main.css" %>
</style>
<body>
<jsp:include page="menu.jsp">
    <jsp:param name="list" value="active" />
</jsp:include>
<div class="main-page">
    <div class="table" align="center">
        <caption><h2>List of Vehicles</h2></caption>
        <table border="1" cellpadding="13" class="table">
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
                <th>Edit / Delete</th>
            </tr>
            </thead>
        </table>
    </div>
    <div class="filter">
        <jsp:include page="filter.jsp"/>
    </div>
</div>
<script>
    <%@include file="js/getAll.js"%>
</script>
</body>
</html>