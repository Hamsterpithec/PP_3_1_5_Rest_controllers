package PP_3_1_2_Spring_security.dao;

import PP_3_1_2_Spring_security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByName(String username);
}
