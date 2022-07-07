package pl.sda.arppl4.arppl4_spring_rental.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.arppl4.arppl4_spring_rental.model.Car;
import pl.sda.arppl4.arppl4_spring_rental.repository.ICarRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarService {
    private final ICarRepository iCarRepository;

    public List<Car> getAllCars() {
        return iCarRepository.findAll();
    }

    public void addCar(Car car) {
        iCarRepository.save(car);
    }
}
