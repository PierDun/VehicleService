<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <ul class="navbar-nav">
        <li class="nav-item ${param.list}">
            <a class="nav-link" href="/CRUDWithRestAndJSP/main-page.jsp">Vehicle List</a>
        </li>
        <li class="nav-item ${param.new}">
            <a class="nav-link" href="/CRUDWithRestAndJSP/api/pages/add-vehicle-form">New Vehicle</a>
        </li>
        <li class="nav-item">
            <a class="nav-link ${param.id}" href="/CRUDWithRestAndJSP/api/pages/get-by-id-form">Get Vehicle by id</a>
        </li>
    </ul>
</nav>
</body>
</html>