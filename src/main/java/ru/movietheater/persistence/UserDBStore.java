package ru.movietheater.persistence;

import net.jcip.annotations.ThreadSafe;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Repository;
import ru.movietheater.model.User;

import java.sql.*;
import java.util.Optional;

@Repository
@ThreadSafe
public class UserDBStore {

    private final BasicDataSource pool;

    public UserDBStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public Optional<User> addUser(User user) throws SQLIntegrityConstraintViolationException {
        Optional<User> userOptional = Optional.empty();
        try (Connection cn = pool.getConnection();
             PreparedStatement pr =
                     cn.prepareStatement("insert into users(firstname, surname, email, phone) VALUES (?, ?, ?, ?)",
                             PreparedStatement.RETURN_GENERATED_KEYS)) {
            pr.setString(1, user.getFirstName());
            pr.setString(2, user.getSurName());
            pr.setString(3, user.getEmail());
            pr.setString(4, user.getPhone());
            pr.execute();
            try (ResultSet resultSet = pr.getGeneratedKeys()) {
                if (resultSet.next()) {
                    user.setUserId(resultSet.getInt(1));
                    userOptional = Optional.of(user);
                }
            }
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                throw new SQLIntegrityConstraintViolationException();
            } else {
                e.printStackTrace();
            }
        }
        return userOptional;
    }

    public Optional<User> getUserByAttr(User user) {
        Optional<User> optionalUser = Optional.empty();
        try (Connection cn = pool.getConnection();
             PreparedStatement pr =
                     cn.prepareStatement("select * from users where firstname = ? and surName = ? and email = ? and phone = ?")) {
            pr.setString(1, user.getFirstName());
            pr.setString(2, user.getSurName());
            pr.setString(3, user.getEmail());
            pr.setString(4, user.getPhone());
            try (ResultSet resultSet = pr.executeQuery()) {
                if (resultSet.next()) {
                    optionalUser = Optional.of(
                            new User(resultSet.getInt("id"),
                                    resultSet.getString("firstName"),
                                    resultSet.getString("surName"),
                                    resultSet.getString("email"),
                                    resultSet.getString("phone")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return optionalUser;
    }

    public Optional<User> getUserById(int id) {
        Optional<User> optionalUser = Optional.empty();
        try (Connection cn = pool.getConnection();
             PreparedStatement pr =
                     cn.prepareStatement("select * from users where id = ?")) {
            pr.setInt(1, id);
            try (ResultSet resultSet = pr.getResultSet()) {
                if (resultSet.next()) {
                    optionalUser = Optional.of(
                            new User(resultSet.getInt("id"),
                                    resultSet.getString("firstName"),
                                    resultSet.getString("surName"),
                                    resultSet.getString("email"),
                                    resultSet.getString("phone")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return optionalUser;
    }
}
