package ru.lanit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.lanit.service.CarService;
import ru.lanit.dto.request.Car;
import javax.validation.Valid;

@RestController
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @RequestMapping(value = "/car", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody @Valid Car requestDto) {
        carService.save(requestDto);
        return ResponseEntity.ok().build();
    }
}
