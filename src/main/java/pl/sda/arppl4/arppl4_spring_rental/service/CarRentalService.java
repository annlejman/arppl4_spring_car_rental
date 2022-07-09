package pl.sda.arppl4.arppl4_spring_rental.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;
import pl.sda.arppl4.arppl4_spring_rental.exception.CarNotAvailableException;
import pl.sda.arppl4.arppl4_spring_rental.model.Car;
import pl.sda.arppl4.arppl4_spring_rental.model.CarRental;
import pl.sda.arppl4.arppl4_spring_rental.model.dto.CarDTO;
import pl.sda.arppl4.arppl4_spring_rental.model.dto.RentCarRequest;
import pl.sda.arppl4.arppl4_spring_rental.repository.ICarRentalRepository;
import pl.sda.arppl4.arppl4_spring_rental.repository.ICarRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarRentalService {
    private final ICarRentalRepository iCarRentalRepository;
    private final ICarRepository iCarRepository;

    public List<CarDTO> getAllAvailableCars() {
        List<Car> carList = iCarRepository.findAll();

        List<CarDTO> cars = new ArrayList<>();
        for (Car car : carList) {
            if (!isRented(car)) {
                //auto jest dostępne, zwróc je
                cars.add(car.mapToCarDTO());
            }
        }

        return cars;
    }

    private boolean isRented(Car car) {
        for (CarRental carRental : car.getCarRentals()) {
            if (carRental.getReturnDateTime() == null) {
                return true;
            }
        }
        return false;
    }

    public void rentCar(Long carId, RentCarRequest request) {
        Optional<Car> optionalCar = iCarRepository.findById(carId);
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            //jeśli nie jest wynajęty (to jest ok)
            if (!isRented(car)) {
                CarRental carRental = mapRentCarRequestToCarRental(request);
                carRental.setCar(car);

                iCarRentalRepository.save(carRental);
                return;
            }
            throw new CarNotAvailableException("Car not available,id: " + carId);
        }
        throw new EntityNotFoundException("Unable to find car with id: " + carId);
    }

    private CarRental mapRentCarRequestToCarRental(RentCarRequest request) {
        return new CarRental(
                request.getNameOfTheClient(),
                request.getSurnameOfTheClient(),
                request.getHourlyPrice());
    }

    public void returnCar(Long carId) {
        Optional<Car> carOptional = iCarRepository.findById(carId);
        if (carOptional.isPresent()) {
            Car car = carOptional.get();

            Optional<CarRental> optionalCarRental = findActiveCarRental(car);
            if (optionalCarRental.isPresent()) {
                CarRental carRental = optionalCarRental.get();
                //ustaw datę zakończenia wynajmu
                carRental.setReturnDateTime(LocalDateTime.now());
                //dokonujemy aktualizacji w bazie
                iCarRentalRepository.save(carRental);
                return;
            }
            throw new CarNotAvailableException("Car not rented,id: " + carId);
        }
        throw new EntityNotFoundException("Unable to find car with id: " + carId);
    }


    private Optional<CarRental> findActiveCarRental(Car car) {
        for (CarRental carRental : car.getCarRentals()) {
            if (carRental.getReturnDateTime() == null) {
                return Optional.of(carRental);
            }
        }
        // nie znaleźliśmy aktywnego wynajmu
        return Optional.empty();

    }
}

