async function addVehicle() {
    const addVehicleForm = document.forms.namedItem("addVehicleForm");
    let formData = new FormData(addVehicleForm);
    const newVehicle = JSON.stringify({
        id: 0,
        name: formData.get('name'),
        coordinates: {
            id : 0,
            x: formData.get('x'),
            y: formData.get('y')
        },
        enginePower: formData.get('enginePower'),
        numberOfWheels: formData.get('numberOfWheels'),
        type: formData.get('type'),
        fuelType: formData.get('fuelType')}
    );

    let response = await fetch('/VehicleService/api/vehicles', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: newVehicle
    });

    if (response.ok) {
        window.location = '/VehicleService/main-page.jsp';
    } else {
        let errorMsg = await response.text();
        $('.error-msg__text').remove();
        $('.error-msg').append("<p class='error-msg__text alert alert-danger'>" + errorMsg + "</p>");
    }
}