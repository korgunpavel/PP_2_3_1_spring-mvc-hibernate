package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao{

    @PersistenceContext
    private final EntityManager entityManager;

    public UserDaoImp(EntityManagerFactory managerFactory) {
        entityManager = managerFactory.createEntityManager();
    }

    @Override
    public List<User> index() {
        return entityManager.createQuery("from User u", User.class).getResultList();
    }

    @Override
    public User show(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(long id, User updUser) {
        entityManager.merge(updUser);
    }

    @Override
    public void delete(long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

}
