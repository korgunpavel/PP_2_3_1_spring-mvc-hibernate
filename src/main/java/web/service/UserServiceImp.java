package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
@Transactional(readOnly = true)
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class UserServiceImp implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> index() {
        return userDao.index();
    }

    @Override
    public User show(long id) {
        return userDao.show(id);
    }

    @Transactional
    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Transactional
    @Override
    public void update(long id, User updUser) {
        userDao.update(id, updUser);
    }

    @Transactional
    @Override
    public void delete(long id) {
        userDao.delete(id);
    }
}
