package PP_3_1_2_Spring_security.service;

import PP_3_1_2_Spring_security.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getRoles();
    void addRole(Role role);
}