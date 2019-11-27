package ru.lanit.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.lanit.entity.Car;

public interface CarRepositoryInterface extends CrudRepository<Car,Long> {
    @Query("SELECT COUNT(DISTINCT vendor) FROM Car ")
    long getCountUniqueVendor();
}
