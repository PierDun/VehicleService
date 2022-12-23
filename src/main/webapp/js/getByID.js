async function sendGetRequest () {
    let id = $('#id').val();
    let url = "/CRUDWithRestAndJSP/api/vehicles/" + id;
    let response = await fetch(url);
    if (response.ok) {
        let json = await response.json();
        $('.table-rows').remove();

        $('#table').innerHTML += '<tr class="table-rows"><th>' + json.id +
            '</th><th>' + json.name + '</th>' +
            '</th><th>' + json.coordinates.x + '</th>' +
            '</th><th>' + json.coordinates.y + '</th>' +
            '</th><th>' + json.creationDate + '</th>' +
            '</th><th>' + json.enginePower + '</th>' +
            '</th><th>' + json.numberOfWheels + '</th>' +
            '</th><th>' + json.type + '</th>' +
            '</th><th>' + json.fuelType + '</th></tr>';

        $('#info').innerText = 'vehicle with id ' + id;
    } else {
        let error = await response.text();
        $('.mx-auto__text').remove();
        $('.mx-auto').append('<h7>' + error + '</h7>');
    }
}