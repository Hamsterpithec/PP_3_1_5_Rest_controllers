package PP_3_1_2_Spring_security.service;

import PP_3_1_2_Spring_security.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> getAllRoles();
    Set<Role> findRoles(List<Long> roles);
}