package PP_3_1_2_Spring_security.dao;


import PP_3_1_2_Spring_security.model.Role;
import PP_3_1_2_Spring_security.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;


@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(long id) {
        entityManager.remove(findById(id));
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User findByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery("from User where name = :username", User.class);
        query.setParameter("username", username);

        return query.getSingleResult();
    }
}
