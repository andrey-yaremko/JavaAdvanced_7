package ua.lviv.lgs.dao.service.impl;

import org.apache.log4j.Logger;
import ua.lviv.lgs.dao.UserDao;
import ua.lviv.lgs.dao.impl.UserDaoImpl;
import ua.lviv.lgs.dao.service.UserService;
import ua.lviv.lgs.domain.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
    private static UserService userServiceImpl;
    private UserDao userDao;

    public UserServiceImpl(){
        try {
            userDao = new UserDaoImpl();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static UserService getUserService() {
        if(userServiceImpl == null) {
            userServiceImpl = new UserServiceImpl();
        }
        return userServiceImpl;
    }

    @Override
    public User create(User user) throws SQLException {
        return userDao.create(user);
    }

    @Override
    public User read(Integer id) throws SQLException {
        return userDao.read(id);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public void delete(Integer id) throws SQLException {
    userDao.delete(id);
    }

    @Override
    public List<User> readAll() throws SQLException {
        return userDao.readAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }
}
