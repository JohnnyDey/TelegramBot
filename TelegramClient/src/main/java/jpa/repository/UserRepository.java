package jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.telegram.telegrambots.api.objects.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
