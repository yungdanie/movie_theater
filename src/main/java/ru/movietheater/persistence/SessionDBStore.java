package ru.movietheater.persistence;

import net.jcip.annotations.ThreadSafe;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Repository;
import ru.movietheater.model.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@ThreadSafe
public class SessionDBStore {

    private final BasicDataSource pool;
    private final TicketDBStore ticketDBStore;

    private final HallDBStore hallDBStore;

    public SessionDBStore(BasicDataSource pool, TicketDBStore ticketDBStore, HallDBStore hallDBStore) {
        this.pool = pool;
        this.ticketDBStore = ticketDBStore;
        this.hallDBStore = hallDBStore;
    }

    public Optional<Session> getSessionById(int id) {
        Optional<Session> session = Optional.empty();
        try (Connection cn = pool.getConnection();
             PreparedStatement pr = cn.prepareStatement("select * from sessions where session_id = ?")) {
            pr.setInt(1, id);
            try (ResultSet resultSet = pr.executeQuery()) {
                if (resultSet.next()) {
                    session = Optional.of(new Session(resultSet.getInt("session_id"),
                            resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getTimestamp("start_time").toLocalDateTime(),
                            resultSet.getTimestamp("end_time").toLocalDateTime(),
                            hallDBStore.getHallById(resultSet.getInt("hall_id")),
                            ticketDBStore.getAllTickets(resultSet.getArray("tickets_id"))
                            ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return session;
    }

    public List<Session> getActualSessions() {
        List<Session> sessions = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement pr =
                     cn.prepareStatement("select * from sessions where actual = ? order by start_time ASC")) {
            pr.setBoolean(1, true);
            try (ResultSet resultSet = pr.executeQuery()) {
                while (resultSet.next()) {
                    sessions.add((new Session(resultSet.getInt("session_id"),
                            resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getTimestamp("start_time").toLocalDateTime(),
                            resultSet.getTimestamp("end_time").toLocalDateTime(),
                            hallDBStore.getHallById(resultSet.getInt("hall_id")),
                            ticketDBStore.getAllTickets(resultSet.getArray("tickets_id"))
                    )));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sessions;
    }
}
