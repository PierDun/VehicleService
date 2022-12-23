window.onload = async function getVehicleForEdit () {
    let id = window.location.href.substring(window.location.href.indexOf("id") + 3);
    let url = "/CRUDWithRestAndJSP/api/vehicles/" + id;
    let response = await fetch(url);
    if (response.ok) {
        let json = await response.json();

        $('input[name="id"]').val(json.id);
        $('input[name="name"]').val(json.name);
        $('input[name="coor_id"]').val(json.coordinates.id);
        $('input[name="x"]').val(json.coordinates.x);
        $('input[name="y"]').val(json.coordinates.y);
        $('input[name="creationDate"]').val(json.creationDate);
        $('input[name="enginePower"]').val(json.enginePower);
        $('input[name="numberOfWheels"]').val(json.numberOfWheels);
        $('select[name="type"]').val(json.type);
        $('select[name="fuelType"]').val(json.fuelType);
    }
}

async function updateVehicle() {
    const updateDragonForm = document.forms.namedItem("updateVehicleForm");
    let formData = new FormData(updateDragonForm);
    const newVehicle = JSON.stringify({id: formData.get('id'),
        name: formData.get('name'),
        creationDate: formData.get('creationDate'),
        coordinates: {id: formData.get('coor_id') ,x: formData.get('x') , y: formData.get('y')},
        enginePower: formData.get('enginePower'),
        numberOfWheels: formData.get('numberOfWheels'),
        type: formData.get('type'),
        fuelType: formData.get('fuelType')});

    let response = await fetch('/CRUDWithRestAndJSP/api/vehicles/' + formData.get('id'), {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: newVehicle
    });

    await getErrorMsg(response);
}

async function getErrorMsg(response) {
    if (response.ok) {
        window.location = '/CRUDWithRestAndJSP/main-page.jsp';
    } else {
        let errorMsg = await response.text();
        $('.error-msg__text').remove();
        $('.error-msg').append("<p class='error-msg__text alert alert-danger'>" + errorMsg + "</p>");
    }
}