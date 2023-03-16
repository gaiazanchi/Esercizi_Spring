package co.develhope.nosql.repositories;

import co.develhope.nosql.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
