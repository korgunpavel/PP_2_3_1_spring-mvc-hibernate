package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    List<User> index();
    User show(long id);
    void save(User user);
    void update(long id, User updUser);
    void delete(long id);
}
