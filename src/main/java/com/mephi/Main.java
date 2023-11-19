package com.mephi;

import com.mephi.model.User;
import com.mephi.service.UserService;
import com.mephi.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализовать алгоритм работы программы здесь


        UserService service = new UserServiceImpl();

        service.createUsersTable();

        List<User> list = new ArrayList<>();

        list.add( new User("Nick", "Nicholson", (byte) 20 ) ) ;
        list.add( new User("James", "Thompson", (byte) 33 ) ) ;
        list.add( new User("Kate", "Adamson", (byte) 25 ) );
        list.add( new User("Joseph", "Connor", (byte) 40 ) ) ;

        for(User user : list) {
            service.saveUser( user );
        }

        for(User user : service.getAllUsers()){
            System.out.println(user);
        }

        service.cleanUsersTable();
        service.dropUsersTable();


    }
}
