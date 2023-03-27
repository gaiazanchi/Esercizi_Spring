package co.develhope.Repo01.repositories;

import co.develhope.Repo01.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CarRepository extends JpaRepository<Car, Integer> {
}
