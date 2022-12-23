package rest.mapper;

import rest.dto.CoordinatesDto;
import rest.dto.VehicleDto;
import rest.model.Coordinates;
import rest.model.Vehicle;
import rest.model.enums.FuelType;
import rest.model.enums.VehicleType;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class VehicleMapper {
    public static VehicleDto vehicleToDto (Vehicle vehicle) {
        VehicleDto dto = new VehicleDto();
        dto.setId(vehicle.getId());
        dto.setName(vehicle.getName());
        dto.setEnginePower(vehicle.getEnginePower());
        dto.setNumberOfWheels(vehicle.getNumberOfWheels());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss z");
        dto.setCreationDate(vehicle.getCreationDate().format(formatter));

        dto.setFuelType(vehicle.getFuelType().name());
        dto.setType(vehicle.getType().name());

        CoordinatesDto coordinatesDto = new CoordinatesDto();
        coordinatesDto.setId(vehicle.getCoordinates().getId());
        coordinatesDto.setX(vehicle.getCoordinates().getX());
        coordinatesDto.setY(vehicle.getCoordinates().getY());

        dto.setCoordinates(coordinatesDto);

        return dto;
    }

    public static Vehicle dtoToVehicle (VehicleDto dto) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(dto.getId());
        vehicle.setName(dto.getName());
        vehicle.setEnginePower(dto.getEnginePower());
        vehicle.setNumberOfWheels(dto.getNumberOfWheels());

        vehicle.setCreationDate(ZonedDateTime.parse(dto.getCreationDate(), DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss z")));

        vehicle.setFuelType(FuelType.valueOf(dto.getFuelType()));
        vehicle.setType(VehicleType.valueOf(dto.getType()));

        Coordinates coordinates = new Coordinates();
        coordinates.setId(dto.getCoordinates().getId());
        coordinates.setX(dto.getCoordinates().getX());
        coordinates.setY(dto.getCoordinates().getY());

        vehicle.setCoordinates(coordinates);

        return vehicle;
    }
}