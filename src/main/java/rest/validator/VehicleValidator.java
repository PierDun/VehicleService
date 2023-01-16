package rest.validator;

import rest.dto.VehicleDto;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import rest.model.Error;

public class VehicleValidator {
    private final CoordinatesValidator coordinatesValidator = new CoordinatesValidator();

    public void validate (VehicleDto vehicle) throws ValidateFieldsException, IllegalAccessException {
        List<Error> errorList = new ArrayList<>();
        for (Field f : VehicleDto.class.getDeclaredFields()) {
            f.setAccessible(true);
            if (f.get(vehicle) == null)
                errorList.add(new Error(700, (String.format("Vehicle's %s is not specified", f.getName())), f.getName()));
        }

        double enginePower = 0.0;
        long numberOfWheels = 0L;

        if (vehicle.getName() != null && vehicle.getName().isEmpty())
            errorList.add(new Error(701, "Vehicle's name should not be empty","Name"));

        if (checkEnginePower(vehicle.getEnginePower())) {
            enginePower = Double.parseDouble(vehicle.getEnginePower());
            if (enginePower <= 0)
                errorList.add(new Error(701,"Vehicle's engine power should be bigger than 0", "Engine Power"));
        } else
            errorList.add(new Error(701,"Vehicle's engine power should be a number", "Engine Power"));

        if (checkNumberOfWheels(vehicle.getNumberOfWheels())) {
            numberOfWheels = Long.parseLong(vehicle.getNumberOfWheels());
            if (numberOfWheels <= 0)
                errorList.add(new Error(701,"Vehicle's number of wheels should be bigger than 0", "Number of Wheels"));
        } else
            errorList.add(new Error(701,"Vehicle's number of wheels should be a number", "Number of Wheels"));

        if (vehicle.getCreationDate() == null)
            errorList.add(new Error(701,"Vehicle's creation date should not be empty", "Time"));

        if (vehicle.getType() == null)
            errorList.add(new Error(701,"Vehicle's type should not be empty", "Vehicle Type"));

        if (vehicle.getFuelType() == null)
            errorList.add(new Error(701,"Vehicle's fuel type should not be empty", "Fuel Type"));

        errorList.addAll(coordinatesValidator.validate(vehicle.getCoordinates()));

        if (errorList.size() > 0)
            throw new ValidateFieldsException(errorList);
    }

    private boolean checkEnginePower (String val) {
        boolean isDouble = true;
        try {
            Double.valueOf(val);
            return isDouble;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private boolean checkNumberOfWheels (String val) {
        boolean isLong = true;
        try {
            Long.valueOf(val);
            return isLong;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}