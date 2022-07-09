package pl.sda.arppl4.arppl4_spring_rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.arppl4.arppl4_spring_rental.model.Car;
import pl.sda.arppl4.arppl4_spring_rental.model.CarRental;

public interface ICarRentalRepository extends JpaRepository<CarRental, Long> {
}
