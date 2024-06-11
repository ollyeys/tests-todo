package Helpers;

import java.sql.*;

public class DatabaseHelper {

    private static final String jdbcURL =  ConfProperties.getProperty("DB_URL");
    private static final String jdbcUsername =ConfProperties.getProperty("DB_USER");
    private static final String jdbcPassword = ConfProperties.getProperty("DB_PASSWORD");

//    private static final String jdbcUsername = ConfProperties.getProperty("DB_USER");
//    private static final String jdbcPassword = ConfProperties.getProperty("DB_PASSWORD");

    private static final String SELECT_USER = "select name, surname, username, password from users where name = ? and surname = ? and username = ? and password = ?;";

    private static final String SELECT_TASK = "select title, description, status from todos where title = ? and description = ? and status = ?;";


    private static final String DELETE_TASK = "delete * from todos where user_id = ? and id = ?;";

    private static final String DELETE_USERS = "truncate table users restart identity cascade;";

    private static final String DELETE_TASKS = "truncate table todos restart identity;";



    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        System.out.println(jdbcURL);
        System.out.println(jdbcUsername);
        System.out.println(jdbcPassword);
        Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        return connection;
    }

    public static boolean isUserExists(String name, String surname, String login, String password) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, login);
            preparedStatement.setString(4, password);


            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(preparedStatement);
            return resultSet.next();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean isTaskExists(String title, String description, Boolean status) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TASK)) {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
//            preparedStatement.setString(3, targetdate);
            preparedStatement.setBoolean(3, status);

            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean clearDB() {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USERS);
            PreparedStatement preparedStatement1 = connection.prepareStatement(DELETE_TASKS)) {
            int rowsAffectedUsers = preparedStatement.executeUpdate();
            int rowsAffectedTasks = preparedStatement1.executeUpdate();
            return rowsAffectedUsers > 0 && rowsAffectedTasks > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }

    }

}
