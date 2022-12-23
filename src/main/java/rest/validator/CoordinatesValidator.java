package rest.validator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import rest.dto.CoordinatesDto;
import rest.model.Error;

public class CoordinatesValidator {
    public List<Error> validate(CoordinatesDto coordinates) throws IllegalAccessException, ValidateFieldsException {
        List<Error> errorList = new ArrayList<>();
        if (coordinates == null) return errorList;

        for (Field f : CoordinatesDto.class.getDeclaredFields()) {
            f.setAccessible(true);
            if (f.get(coordinates) == null)
                errorList.add(new Error(700, String.format("Coordinates' %s is not specified", f.getName()), f.getName()));
        }

        if (errorList.size() == 0) {
            if (coordinates.getY() != null && coordinates.getY() > 144)
                errorList.add(new Error(701, "Coordinates' y maximum value is 144", "y"));
        }

        if (errorList.size() > 0)
            throw new ValidateFieldsException(errorList);

        return errorList;
    }
}