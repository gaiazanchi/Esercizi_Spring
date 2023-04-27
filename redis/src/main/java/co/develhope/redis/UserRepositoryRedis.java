package co.develhope.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryRedis extends CrudRepository<UserRedis, Long> {
}
