package ua.lviv.lgs.dao.impl;

import org.apache.log4j.Logger;
import ua.lviv.lgs.dao.UserDao;
import ua.lviv.lgs.domain.Product;
import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.utils.ConnectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{
    private static String READ_ALL = "select * from user";
    private static String CREATE = "insert into user (email,first_name,last_name,password,role) values (?,?,?,?,?)";
    private static String READ_BY_ID = "select * from user where id = ?";
    private static String UPDATE_BY_ID = "update product set email = ?, first_name = ?, last_name = ?, role = ? where id = ?";
    private static String DELETE_BY_ID = "delete from user where id - ?";
    private static String READ_BY_EMAIL = "select * from user where email=?";

    private static Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    private Connection connection;
    private PreparedStatement preparedStatement;

    public UserDaoImpl() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        connection = ConnectionUtils.openConection();
    }
    @Override
    public User create(User user) {
        try {
            preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirst_name());
            preparedStatement.setString(3, user.getLast_name());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getRole());
            preparedStatement.executeUpdate();

            ResultSet result = preparedStatement.getGeneratedKeys();
            result.next();
            user.setId(result.getInt(1));

        } catch (Exception e) {
            LOGGER.error(e);
        }

        return user;
    }

    @Override
    public User read(Integer id) {
        User user = null;
        try {
            preparedStatement = connection.prepareStatement(READ_BY_ID);
            preparedStatement.setInt(1,id);
            ResultSet result = preparedStatement.executeQuery();
            result.next();

            Integer user_id = result.getInt("id");
            String email = result.getString("email");
            String first_name = result.getString("first_name");
            String last_name =result.getString("last_name");
            String role = result.getString("role");
            String password = result.getString("password");

            user = new User(user_id,email,first_name,last_name,role,password);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return user;
    }

    @Override
    public User update(User user) {
        try {
            preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
            preparedStatement.setString(1,user.getEmail());
            preparedStatement.setString(2, user.getFirst_name());
            preparedStatement.setString(3,user.getLast_name());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return user;
    }

    @Override
    public void delete(Integer id) {
        try {
            preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public List<User> readAll(){
            List<User> userRecords = new ArrayList<>();
            try {
                preparedStatement = connection.prepareStatement(READ_ALL);
                ResultSet result = preparedStatement.executeQuery();
                while (result.next()){
                    Integer user_id = result.getInt("id");
                    String email = result.getString("email");
                    String first_name = result.getString("first_name");
                    String last_name =result.getString("last_name");
                    String role = result.getString("role");
                    String password = result.getString("password");

                    userRecords.add(new User(user_id,email,first_name,last_name,role,password));
                }
            } catch (SQLException e) {
                LOGGER.error(e);;
            }
            return userRecords;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = null;

        try {
            preparedStatement = connection.prepareStatement(READ_BY_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet result = preparedStatement.executeQuery();
            result.next();

            Integer user_id = result.getInt("id");
            String first_name = result.getString("first_name");
            String last_name = result.getString("last_name");
            String role = result.getString("role");
            String password = result.getString("password");

            user = new User(user_id, first_name, last_name, email, password, role);

        } catch (Exception e) {
            LOGGER.error(e);
        }

        return user;
    }
}
