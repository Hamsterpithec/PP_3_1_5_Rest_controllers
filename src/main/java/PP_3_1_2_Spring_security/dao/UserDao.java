package PP_3_1_2_Spring_security.dao;




import PP_3_1_2_Spring_security.model.Role;
import PP_3_1_2_Spring_security.model.User;

import java.util.List;
import java.util.Set;

public interface UserDao {
    List<User> getAllUsers();
    User findById(Long id);
    void addUser(User user, Set<Role> roles);
    void deleteUser(long id);
    void updateUser(User user, Set<Role> roles);
    User findByUsername(String username);
}
