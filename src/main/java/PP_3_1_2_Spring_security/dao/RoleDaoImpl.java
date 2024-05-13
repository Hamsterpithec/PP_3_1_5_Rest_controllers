package PP_3_1_2_Spring_security.dao;

import PP_3_1_2_Spring_security.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("from Role", Role.class).getResultList();
    }

    @Override
    public Set<Role> findRoles(Set<Long> roleIds) {
        TypedQuery<Role> q = entityManager.createQuery("select distinct r from Role r where r.id in :role", Role.class);
        q.setParameter("role", roleIds);
        return new HashSet<>(q.getResultList());
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

}
