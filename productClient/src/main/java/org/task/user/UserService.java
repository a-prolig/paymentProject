package org.task.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao){
        this.userDao = userDao;
    }

    public void create(User user) {
        userDao.save(user.getId(), user.getUserName());
    }

    public void delete(Long id) {
        userDao.delete(id);
    }

    public User findUser(Long id) {
        return userDao.getUser(id);
    }

    public List<User> findAll() {
        return userDao.getAll();
    }

}
