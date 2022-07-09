package pl.sda.arppl4.arppl4_spring_rental.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.sda.arppl4.arppl4_spring_rental.model.Car;
import pl.sda.arppl4.arppl4_spring_rental.model.dto.CarDTO;
import pl.sda.arppl4.arppl4_spring_rental.service.CarService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/car")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping("/list")
    public List<CarDTO> getAllCars() {
        log.info("Wywołano listę aut.");
        List<CarDTO> list = carService.getAllCars();
        return list;
    }

    @PostMapping("/add")
    public void addCar(@RequestBody Car car) {
        log.info("Wywołano dodanie auta: " + car);
        carService.addCar(car);

    }

    @DeleteMapping("/delete")
    public void deleteCar(@PathVariable(name = "identifier") Long identyfikator) {
        log.info("Wywołano usunięcie auta: " + identyfikator);
        carService.deleteById(identyfikator);
    }

    @PatchMapping("/update")
    public void updateCar(@RequestBody Car car) {
        log.info("Wywołano aktualizację auta: " + car);
        carService.updateCar(car);

    }
}
