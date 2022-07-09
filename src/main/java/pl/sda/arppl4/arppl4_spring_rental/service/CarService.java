package pl.sda.arppl4.arppl4_spring_rental.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.sda.arppl4.arppl4_spring_rental.model.Car;
import pl.sda.arppl4.arppl4_spring_rental.model.dto.CarDTO;
import pl.sda.arppl4.arppl4_spring_rental.repository.ICarRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarService {
    private final ICarRepository iCarRepository;


    public List<CarDTO> getAllCars() {
        List<Car> carList = iCarRepository.findAll();

        List<CarDTO> cars = new ArrayList<>();
        for (Car car : carList) {
            cars.add(car.mapToCarDTO());
        }

        return cars;
    }
    public void addCar(Car car) {
        iCarRepository.save(car);
    }

    public void deleteById(Long identyfikator) {
    iCarRepository.deleteById(identyfikator);
    }

    public void updateCar(Car daneAktualizujące) {
        Long identifier = daneAktualizujące.getId();

        Optional<Car> carOptional = iCarRepository.findById(identifier);
        if (carOptional.isPresent()) {
            Car editedCar = carOptional.get();

            if (daneAktualizujące.getName() != null) {
                editedCar.setName(daneAktualizujące.getName());
            }
            if (daneAktualizujące.getProductionDate() != null) {
                editedCar.setProductionDate(daneAktualizujące.getProductionDate());

            }
            if (daneAktualizujące.getBodyType() != null) {
                editedCar.setBodyType(daneAktualizujące.getBodyType());
            }
            if (daneAktualizujące.getGearBox() != null) {
                editedCar.setGearBox(daneAktualizujące.getGearBox());
            }
            if (daneAktualizujące.getEngineCapacity() != null) {
                editedCar.setEngineCapacity(daneAktualizujące.getEngineCapacity());
            }
            if (daneAktualizujące.getNumberOfSeats() != null) {
                editedCar.setNumberOfSeats(daneAktualizujące.getNumberOfSeats());
            }

            iCarRepository.save(editedCar);
            log.info("Auto zostało zapisane");
            return;
        }
        throw new EntityNotFoundException("Nie znaleziono auta o id: " + daneAktualizujące.getId());
    }
}


