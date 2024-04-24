package PP_3_1_2_Spring_security.dao;

import PP_3_1_2_Spring_security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
