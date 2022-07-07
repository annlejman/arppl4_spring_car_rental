package pl.sda.arppl4.arppl4_spring_rental.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;
    private  String brand;
    private LocalDate productionDate;
    @Enumerated(EnumType.STRING)
    private  BodyType bodyType;
    private Double numberOfSeats;
    @Enumerated(EnumType.STRING)
    private  GearBox gearBox;
    private Double engineCapacity;

}
