package com.mephi.dao;

import com.mephi.model.User;
import com.mephi.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao{

    @Override
    public void createUsersTable() {

        String statement = "CREATE TABLE IF NOT EXISTS Users (" +
                "id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY," +
                "name VARCHAR(20) NOT NULL," +
                "lastName VARCHAR(20) NOT NULL," +
                "age SMALLINT NOT NULL" +
                ")";


        try(Connection connection = Util.getConnectionJDBC()) {

            connection.createStatement().executeUpdate( statement );

        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {

        String statement = "DROP TABLE IF EXISTS Users CASCADE";

        try(Connection connection = Util.getConnectionJDBC()) {

            connection.createStatement().executeUpdate( statement );

        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void saveUser(User user) {

        String insertStatement =
                "INSERT INTO Users (name, lastName, age) VALUES (?,?,?)";
        try(Connection connection = Util.getConnectionJDBC()) {
            PreparedStatement statement = connection.prepareStatement( insertStatement );
            statement.setString( 1, user.getName());
            statement.setString( 2, user.getLastName() );
            statement.setByte(   3, user.getAge() );

            statement.executeUpdate( );

        } catch (SQLException e){
            System.err.println(e.getMessage());
        }


    }

    @Override
    public void removeUserById(long id) {

        String deleteStatement =
                "DELETE FROM Users WHERE id = ?";
        try(Connection connection = Util.getConnectionJDBC()) {
            PreparedStatement statement = connection.prepareStatement( deleteStatement );
//            statement.setInt( 1, new java.util.Scanner(System.in).nextInt() );
            statement.setInt( 1, (int) id );

            statement.executeUpdate( );

        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {

        String selectStmt =
                "SELECT * FROM Users " ;

        List<User> users = new ArrayList<>();

        try(Connection connection = Util.getConnectionJDBC()) {

            ResultSet resultSet = connection.createStatement().executeQuery(selectStmt);

            while(resultSet.next()){

                users.add( new User(resultSet.getString( "name" ),
                        resultSet.getString( "lastName" ),
                        resultSet.getByte( "age" )) );
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return users ;
    }

    @Override
    public void cleanUsersTable() {


        String statement = "TRUNCATE Users ";

        try(Connection connection = Util.getConnectionJDBC()) {

            connection.createStatement().executeUpdate( statement );

        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }
}
