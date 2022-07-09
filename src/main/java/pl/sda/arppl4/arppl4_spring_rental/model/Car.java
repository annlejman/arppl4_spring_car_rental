package pl.sda.arppl4.arppl4_spring_rental.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.sda.arppl4.arppl4_spring_rental.model.dto.CarDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;
    private String brand;
    private LocalDate productionDate;
    @Enumerated(EnumType.STRING)
    private BodyType bodyType;
    private Double numberOfSeats;
    @Enumerated(EnumType.STRING)
    private GearBox gearBox;
    private Double engineCapacity;


    @OneToMany(mappedBy = "car", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @JsonManagedReference
    private Set<CarRental> carRentals;

    public CarDTO mapToCarDTO() {
        return new CarDTO(
                id,
                name,
                brand,
                productionDate,
                bodyType,
                numberOfSeats,
                gearBox,
                engineCapacity
        );
    }
}

