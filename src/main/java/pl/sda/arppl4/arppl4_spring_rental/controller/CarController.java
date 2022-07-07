package pl.sda.arppl4.arppl4_spring_rental.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.sda.arppl4.arppl4_spring_rental.model.Car;
import pl.sda.arppl4.arppl4_spring_rental.service.CarService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/car")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping("/list")
    public List<Car> getAllCars(){
        log.info("Wywołano listę aut.");
        List<Car>list=carService.getAllCars();
        return list;
    }

    @PostMapping("/add")
    public void  addCar(@RequestBody Car car){
        log.info("Wywołano dodanie auta: "+ car);
        carService.addCar(car);

    }
}
