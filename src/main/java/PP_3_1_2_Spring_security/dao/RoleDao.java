package PP_3_1_2_Spring_security.dao;

import PP_3_1_2_Spring_security.model.Role;


import java.util.List;
import java.util.Set;


public interface RoleDao  {

    List<Role> findAll();
    Set<Role> findRoles(List<Long> roles);
    void save(Role role);
}
