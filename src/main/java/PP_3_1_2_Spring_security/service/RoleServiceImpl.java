package PP_3_1_2_Spring_security.service;

import PP_3_1_2_Spring_security.dao.RoleDao;
import PP_3_1_2_Spring_security.model.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Role> getRoles() {
        return roleDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Role> findRoles(List<Long> roles) {
        return roleDao.findRoles(roles);
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        roleDao.save(role);
    }
}
