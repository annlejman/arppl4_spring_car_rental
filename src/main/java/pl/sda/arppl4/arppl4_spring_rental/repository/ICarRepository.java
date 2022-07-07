package pl.sda.arppl4.arppl4_spring_rental.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import pl.sda.arppl4.arppl4_spring_rental.model.Car;
import pl.sda.arppl4.arppl4_spring_rental.service.CarService;

import java.util.List;

public interface ICarRepository extends JpaRepository<Car, Long>{



}
