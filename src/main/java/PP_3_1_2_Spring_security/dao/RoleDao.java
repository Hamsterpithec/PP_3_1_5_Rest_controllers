package PP_3_1_2_Spring_security.dao;

import PP_3_1_2_Spring_security.model.Role;


import java.util.List;


public interface RoleDao  {

    List<Role> findAll();
    Role findById(Long id);
    void save(Role role);
}
