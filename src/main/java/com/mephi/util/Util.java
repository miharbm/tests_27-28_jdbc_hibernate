package com.mephi.util;

import com.mephi.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    // реализовать настройку соеденения с БД

    private static SessionFactory sessionFactory;

    private Util() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }

    static final String DB_URL = "jdbc:postgresql://localhost:5432/test27";
    static final String DB_Driver = "org.postgresql.Driver";


    public static java.sql.Connection getConnectionJDBC()  {
        try {
            Class.forName( DB_Driver ); //Проверяем наличие JDBC драйвера для работы с БД

        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // обработка ошибки  Class.forName
        }

        Connection connection = null; 
        try {
            connection = DriverManager.getConnection( DB_URL, "postgres", "root" );
        } catch ( SQLException e) {
            e.printStackTrace(); // обработка ошибки  Class.forName
        }

        return  connection;
    }
}
