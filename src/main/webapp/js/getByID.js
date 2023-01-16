async function sendGetRequest () {
    let id = $('#id').val();
    let url = "/VehicleService/api/vehicles/" + id;
    let response = await fetch(url);
    if (response.ok) {
        let json = await response.json();
        $('.table-rows').remove();

        $('#table').append('<tr class="table-rows"><th>' + json.id +
            '</th><th>' + json.name + '</th>' +
            '</th><th>' + json.coordinates.x + '</th>' +
            '</th><th>' + json.coordinates.y + '</th>' +
            '</th><th>' + json.creationDate + '</th>' +
            '</th><th>' + json.enginePower + '</th>' +
            '</th><th>' + json.numberOfWheels + '</th>' +
            '</th><th>' + json.type + '</th>' +
            '</th><th>' + json.fuelType + '</th></tr>');

        $('#info').text('Vehicle with id ' + id);
    } else {
        let error = await response.text();
        $('.mx-auto__text').remove();
        $('#error').text(error);
    }
}