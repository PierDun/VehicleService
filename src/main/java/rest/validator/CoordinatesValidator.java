package rest.validator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import rest.dto.CoordinatesDto;
import rest.model.Error;

public class CoordinatesValidator {
    public List<Error> validate(CoordinatesDto coordinates) throws IllegalAccessException {
        List<Error> errorList = new ArrayList<>();
        if (coordinates == null) return errorList;

        for (Field f : CoordinatesDto.class.getDeclaredFields()) {
            f.setAccessible(true);
            if (f.get(coordinates) == null)
                errorList.add(new Error(700, String.format("Coordinates' %s is not specified", f.getName()), f.getName()));
        }

        float x = 0.0F;
        double y = 0.0;

        if (checkX(coordinates.getX()))
            x = Float.parseFloat(coordinates.getX());
        else
            errorList.add(new Error(701, "Coordinates' x value is not number", "x"));

        if (checkY(coordinates.getY())) {
            y = Double.parseDouble(coordinates.getX());
            if (coordinates.getY() != null && y > 144)
                errorList.add(new Error(701, "Coordinates' y maximum value is 144", "y"));
        } else
            errorList.add(new Error(701, "Coordinates' y value is not number", "y"));

        return errorList;
    }

    private boolean checkX (String val) {
        boolean isFloat = true;
        try {
            Float.valueOf(val);
            return isFloat;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private boolean checkY (String val) {
        boolean isDouble = true;
        try {
            Double.valueOf(val);
            return isDouble;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}