const fields = ["Name", "X", "Y", "Date", "Power", "Wheels", "Type", "Fuel"];

fields.forEach((element) => {
    $('.is' + element + 'Disabled').change(function () {
        if ($('.is' + element + 'Disabled').is(':checked')) {
            $('.' + element).removeAttr("disabled");
        } else {
            $('.' + element).attr("disabled", "true");
        }
    });
})

async function filter () {
    const formData = new FormData(document.forms.namedItem("filterForm"));
    let getStr = "/VehicleService/api/vehicles?";
    let selectedColumn = getSelectedColumn();
    let selectedOrder = getSelectedOrder();

    getStr += "sortBy=" + selectedColumn + "&order=" + selectedOrder + "&selectedPage=" + $("#selectedPage").val()
        + "&numberOfRecordsPerPage=" + $("#numberOfRecordsPerPage").val();

    for (let pair of formData.entries()) {
        getStr += '&' + pair[0] + '=' + pair[1];
    }

    fetch(getStr).then(response => response.json()).then(json => createVehicleTable(json));
}

async function powerSum () {
    let response = await fetch('/VehicleService/api/vehicles/engine-power/sum');
    if (response.ok) {
        document.getElementById("sum_result").textContent = await response.text();
    }
}

async function minName () {
    let response = await fetch('/VehicleService/api/vehicles/name/min');
    if (response.ok) {
        let json = await response.json();

        $('.table-rows').remove();
        $('table').append(
            '<tr class="table-rows"><td>' + json.id +
            '</td><td>' + json.name + '</td>' +
            '</td><td>' + json.coordinates.x + '</td>' +
            '</td><td>' + json.coordinates.y + '</td>' +
            '</td><td>' + json.creationDate + '</td>' +
            '</td><td>' + json.enginePower + '</td>' +
            '</td><td>' + json.numberOfWheels + '</td>' +
            '</td><td>' + json.type + '</td>' +
            '</td><td>' + json.fuelType + '</td><td>') +
        `<button class="btn btn-primary mclassNameo mt-2" onclick="showEditForm(${json.id})">Edit</button>` +
        `<button class="btn btn-primary mclassNameo mt-2" onclick="deleteVehicle(${json.id})">Delete</button>` +
        '</td></tr>';
    }
}

async function fuelList () {
    let response = await fetch('/VehicleService/api/vehicles/fuel-type/unique');
    if (response.ok) {
        const text = await response.text();
        $("#list_result").text(text);
    }
}

function changePagesQuantity() {
    let vehiclesQuantity = $("#total").val();
    const numberOfRecordsPerPage = document.getElementById("numberOfRecordsPerPage").value;
    const pagesQuality = Math.ceil(vehiclesQuantity / numberOfRecordsPerPage);
    $('#selectedPage').remove();
    let html = "<select id='selectedPage' name='selectedPage'>";
    for (let i = 1; i < pagesQuality+1; i++) {
        html += '<option value='+ i + '>'+ i + '</option>'
    }
    html += "</select>"
    $('.selectedPage').append(html);
}

async function deleteVehicle(id) {
    let response = await fetch('/VehicleService/api/vehicles/' + id, {method: 'DELETE'});
    if (response.ok) {
        window.location = '/VehicleService/main-page.jsp';
    }
}

function createVehicleTable(json) {
    $('.table-rows').remove();
    for (let i = 0; i < json.length; i++) {
        $('table').append(
            '<tr class="table-rows"><td>' + json[i].id +
            '</td><td>' + json[i].name + '</td>' +
            '</td><td>' + json[i].coordinates.x + '</td>' +
            '</td><td>' + json[i].coordinates.y + '</td>' +
            '</td><td>' + json[i].creationDate + '</td>' +
            '</td><td>' + json[i].enginePower + '</td>' +
            '</td><td>' + json[i].numberOfWheels + '</td>' +
            '</td><td>' + json[i].type + '</td>' +
            '</td><td>' + json[i].fuelType + '</td>' +
            '<td> ' +
            `<button class="btn btn-primary mclassNameo mt-2" onclick="showEditForm(${json[i].id})">Edit</button> ` +
            `<button class="btn btn-primary mclassNameo mt-2" onclick="deleteVehicle(${json[i].id})">Delete</button>` +
            '</td></tr>'
        );
    }
}

function getSelectedColumn() {
    const columns = document.querySelectorAll('input[name="sortBy"]');
    let selectedColumn;
    for (const radioButton of columns) {
        if (radioButton.checked) {
            selectedColumn = radioButton.value;
            break;
        }
    }
    return selectedColumn;
}

function getSelectedOrder() {
    const orders = document.querySelectorAll('input[name="order"]');
    let selectedOrder;
    for (const radioButton of orders) {
        if (radioButton.checked) {
            selectedOrder = radioButton.value;
            break;
        }
    }
    return selectedOrder;
}