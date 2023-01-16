package rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDto {
    Long id;
    String creationDate;
    String name;
    String enginePower;
    String numberOfWheels;
    CoordinatesDto coordinates;
    String type;
    String fuelType;
}