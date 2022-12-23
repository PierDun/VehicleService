package rest.model;

import rest.model.enums.FuelType;
import rest.model.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    @Column(nullable = false)
    private String name; //Поле не может быть null, Строка не может быть пустой

    @OneToOne
    @JoinColumn(name = "coor")
    private Coordinates coordinates; //Поле не может быть null

    @Column(nullable = false, name = "creation_date")
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    @Column(name = "engine_power")
    private double enginePower; //Значение поля должно быть больше 0

    @Column(name = "number_of_wheels")
    private long numberOfWheels; //Значение поля должно быть больше 0

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false, name = "type")
    private VehicleType type; //Поле не может быть null

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false, name = "fuel_type")
    private FuelType fuelType; //Поле не может быть null
}