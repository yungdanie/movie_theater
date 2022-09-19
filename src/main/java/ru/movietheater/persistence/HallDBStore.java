package ru.movietheater.persistence;

import net.jcip.annotations.ThreadSafe;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Repository;
import ru.movietheater.model.Hall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@ThreadSafe
public class HallDBStore {
    private final BasicDataSource pool;

    public HallDBStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public Hall getHallById(int id) {
        Hall hall = new Hall();
        try (Connection cn = pool.getConnection();
             PreparedStatement pr = cn.prepareStatement("select * from hall where id = ?")) {
            pr.setInt(1, id);
            try (ResultSet resultSet = pr.executeQuery()) {
                if (resultSet.next()) {
                    hall = new Hall(resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("columns_count"),
                            resultSet.getInt("rows_count"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hall;
    }
}
