package pl.sda.arppl4.arppl4_spring_rental.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CarRental {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String clientName;
    private String clientSurname;

    @CreationTimestamp
    private LocalDateTime rentDateTime;
    private LocalDateTime returnDateTime;

    private Double price;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    private Car car;

    public CarRental(String clientName, String clientSurname, LocalDateTime rentDateTime, LocalDateTime returnDateTime, Double price, Car car) {
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.rentDateTime = rentDateTime;
        this.returnDateTime = returnDateTime;
        this.price = price;
        this.car = car;
    }

    public CarRental(String clientName, String clientSurname, Double price) {
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.price = price;
    }
}

