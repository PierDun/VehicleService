<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    <%@ include file="/css/filter_style.css" %>
</style>
<body>
<caption><h2>Filter</h2></caption>
<form method="get" class="filter-form paginatorForm" name="numberOfRecordsPerPageForm">
    <p> Items per page </p>
    <select id="numberOfRecordsPerPage" class="form-select" name="numberOfRecordsPerPage" onchange="changePagesQuantity(${vehiclesLength})">
        <option value="5">5</option>
        <option selected value="10">10</option>
        <option value="25">25</option>
    </select>
    <p> Selected Page </p>
    <div class="selectedPage"></div>
    <select id="selectedPage" name="selectedPage">
    </select>
</form>
<ul class="nav nav-tabs filter-tabs" data-tabs="tabs" id="filter-tab">
    <li class="nav-item active">
        <a class="nav-link active" data-toggle="tab" href="#filter">Filter</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" data-toggle="tab" href="#sort">Sort</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" data-toggle="tab" href="#power">Power</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" data-toggle="tab" href="#name">Name</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" data-toggle="tab" href="#fuel">Fuel</a>
    </li>
</ul>
<div class="tab-content">
    <div class="tab-pane fade show active" id="filter">
        <form class="filter-form" name="filterForm">
            <p> <input class="form-check-input isNameDisabled" type="checkbox" > Name:
                <input type="text" name="name" class="form-control Name" disabled/></p>
            <p>Coordinates:</p>
            <p> <input class="form-check-input isXDisabled" type="checkbox" > X: </p>
            <div class="filter-form__range">
                <input type="text" name="x1" class="form-control X" disabled/> - <input type="text" name="x2" class="form-control X" disabled/></div>
            <p> <input class="form-check-input isYDisabled" type="checkbox" > Y: </p>
            <div class="filter-form__range">
                <input type="text" name="y1" class="form-control Y" disabled/> - <input type="text" name="y2" class="form-control Y" disabled/></div>
            <p>Creation date:</p>
            <p> <input class="form-check-input isDateDisabled" type="checkbox" > Date:</p>
            <div class="filter-form__range">
                <input type="date" name="start-creation-date" class="form-control Date" disabled> - <input type="date"
                                                                                                           name="end-creation-date" class="form-control Date" disabled>
            </div>
            <p>Time:</p>
            <div class="filter-form__range">
                <input type="time" name="start-creation-time" value="00:00" class="form-control Date" disabled> - <input type="time"
                                                                                                                         name="end-creation-time"
                                                                                                                         value="23:59" class="form-control Date" disabled>
            </div>

            <p> <input class="form-check-input isPowerDisabled" type="checkbox" > Engine Power:</p>
            <div class="filter-form__range">
                <input type="text" name="enginePower1" class="form-control Power" disabled/>- <input type="text" name="enginePower2" class="form-control Power" disabled/>
            </div>

            <p> <input class="form-check-input isWheelsDisabled" type="checkbox" > Number of Wheels:</p>
            <div class="filter-form__range">
                <input type="text" name="numberOfWheels1" class="form-control Wheels" disabled/>- <input type="text" name="numberOfWheels2" class="form-control Wheels" disabled/>
            </div>

            <p> <input class="form-check-input isTypeDisabled" type="checkbox" > Vehicle Type:
            <div class="form-check form-check-inline">
                <input class="form-check-input Type" name="type" type="checkbox" id="type1" value="PLANE" disabled>
                <label class="form-check-label" for="type1">PLANE</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input Type" name="type" type="checkbox" id="type2" value="BOAT" disabled>
                <label class="form-check-label" for="type2">BOAT</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input Type" name="type" type="checkbox" id="type3" value="SHIP" disabled>
                <label class="form-check-label" for="type3">SHIP</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input Type" name="type" type="checkbox" id="type4" value="MOTORCYCLE" disabled>
                <label class="form-check-label" for="type4">MOTORCYCLE</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input Type" name="type" type="checkbox" id="type5" value="CHOPPER" disabled>
                <label class="form-check-label" for="type5">CHOPPER</label>
            </div>
            </p>

            <p> <input class="form-check-input isFuelDisabled" type="checkbox" > Fuel Type:
            <div class="form-check form-check-inline">
                <input class="form-check-input Fuel" name="fuelType" type="checkbox" id="fuelType1" value="ALCOHOL" disabled>
                <label class="form-check-label" for="fuelType1">ALCOHOL</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input Fuel" name="fuelType" type="checkbox" id="fuelType2" value="MANPOWER" disabled>
                <label class="form-check-label" for="fuelType2">MANPOWER</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input Fuel" name="fuelType" type="checkbox" id="fuelType3" value="PLASMA" disabled>
                <label class="form-check-label" for="fuelType3">PLASMA</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input Fuel" name="fuelType" type="checkbox" id="fuelType4" value="ANTIMATTER" disabled>
                <label class="form-check-label" for="fuelType4">ANTIMATTER</label>
            </div>
            </p>

            <input type="button" class="btn btn-primary mx-auto mt-3" value="find" onclick="filter()"/>
        </form>
    </div>
    <div class="tab-pane fade" id="sort">
        <form class="filter-form" name="sortForm">
            <input type="radio" class="form-check-input" name="sortBy" value="id" checked>  id<BR>
            <input type="radio" class="form-check-input" name="sortBy" value="name">  name<BR>
            <input type="radio" class="form-check-input" name="sortBy" value="x">  x<BR>
            <input type="radio" class="form-check-input" name="sortBy" value="y">  y<BR>
            <input type="radio" class="form-check-input" name="sortBy" value="creationDate">  creation-date<BR>
            <input type="radio" class="form-check-input" name="sortBy" value="enginePower">  engine power<BR>
            <input type="radio" class="form-check-input" name="sortBy" value="numberOfWheels">  number of wheels <BR>
            <input type="radio" class="form-check-input" name="sortBy" value="type">  type<BR>
            <input type="radio" class="form-check-input" name="sortBy" value="fuelType">  fuel type <BR>
            Order: <BR>
            <input type="radio" class="form-check-input" name="order" value="ASC" checked>  ASC<BR>
            <input type="radio" class="form-check-input" name="order" value="DESC">  DESC<BR>
        </form>
    </div>
    <div class="tab-pane fade" id="power">
        <form class="filter-form" name="aggregateFunctions">
            <p> Get Sum of Engine Powers: </p>
            <input type="button" class="btn btn-primary name-filter-btn" value="get result" onclick="powerSum()"/>
            <b id = "sum_result"></b>
        </form>
    </div>
    <div class="tab-pane fade" id="name">
        <form class="filter-form" name="findVehicleWithMinName">
            <p> Get Vehicle with Minimum Name: </p>
            <input type="button" class="btn btn-primary name-filter-btn" value="find" onclick="minName()"/>
        </form>
    </div>
    <div class="tab-pane fade" id="fuel">
        <form class="filter-form" name="findDragonsWithLesserColor">
            <p> Find Unique Fuel Types: </p>
            <input type="button" class="btn btn-primary name-filter-btn" value="find" onclick="fuelList()"/>
            <b id = "list_result"></b>
        </form>
    </div>
</div>
<script>
    <%@ include file="/js/filter.js" %>
</script>
</body>
</html>