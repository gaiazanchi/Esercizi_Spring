package co.develhope.CustomQueries02.repositories;

import co.develhope.CustomQueries02.entities.Flight;
import co.develhope.CustomQueries02.entities.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    List<Flight> findAllByStatus(Status status);

    @Query(value = "SELECT * FROM flights AS f WHERE f.status = :p1 OR f.status = :p2;", nativeQuery = true)
    List<Flight> findAllFlightsWithCertainStatuses(@Param("p1") Status p1, @Param("p2") Status par);

}
