package com.mephi.service;

import com.mephi.dao.UserDao;
import com.mephi.dao.UserDaoHibernateImpl;
import com.mephi.dao.UserDaoJDBCImpl;
import com.mephi.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao users;
    public UserServiceImpl(){

        users = new UserDaoJDBCImpl();
    }
    @Override
    public void createUsersTable() {

        users.createUsersTable();
    }

    @Override
    public void dropUsersTable() {

        users.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        users.saveUser( new User(name, lastName, age) );
    }
    @Override
    public void saveUser(User user) {

        users.saveUser( user );
    }

    @Override
    public void removeUserById(long id) {

        users.removeUserById( id );
    }

    @Override
    public List<User> getAllUsers() {

        return users.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {

        users.cleanUsersTable();
    }
}
