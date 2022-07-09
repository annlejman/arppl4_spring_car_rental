package pl.sda.arppl4.arppl4_spring_rental.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.arppl4.arppl4_spring_rental.model.BodyType;
import pl.sda.arppl4.arppl4_spring_rental.model.GearBox;

import java.time.LocalDate;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class CarDTO {
        private Long carId;
        private String name;
        private String brand;
        private LocalDate productionDate;
        private BodyType bodyType;
        private Double seats;
        private GearBox carGearBox;
        private Double engineCapacity;
    }

