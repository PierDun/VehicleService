window.onload = async function getAllVehicles() {
    let url = "/CRUDWithRestAndJSP/api/vehicles";
    let response = await fetch(url);
    if (response.ok) {
        let json = await response.json();
        $("#numberOfRecordsPerPage").val(10);

        const pagesQuantity = Math.ceil(json.length / 10);
        $('#selectedPage').remove();
        let html = "<select id='selectedPage' name='selectedPage'>";
        for (let i = 1; i < pagesQuantity + 1; i++) {
            html += '<option value='+ i + '>'+ i + '</option>'
        }
        html += "</select>"
        $('.selectedPage').append(html);

        let amount = Math.min(json.length, 10);

        for (let i = 0; i < amount; i++) {
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
}

async function showEditForm (id) {
    window.location = 'api/pages/edit-form?id=' + id;
}