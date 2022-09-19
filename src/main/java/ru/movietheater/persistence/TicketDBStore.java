package ru.movietheater.persistence;

import net.jcip.annotations.ThreadSafe;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Repository;
import ru.movietheater.model.Ticket;
import ru.movietheater.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@ThreadSafe
public class TicketDBStore {

    private final BasicDataSource pool;

    public TicketDBStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public Optional<Ticket> addTicket(Ticket ticket) {
        Optional<Ticket> optionalTicket = Optional.empty();
        try (Connection cn = pool.getConnection();
             PreparedStatement pr =
                     cn.prepareStatement("insert into ticket(session_id, pos_row, cell, user_id) values (?, ?, ?, ?)",
                                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            pr.setInt(1, ticket.getSessionId());
            pr.setInt(2, ticket.getColumn());
            pr.setInt(3, ticket.getRow());
            pr.setInt(4, ticket.getUser().getUserId());
            pr.execute();
            try (ResultSet resultSet = pr.getGeneratedKeys()) {
                if (resultSet.next()) {
                    ticket.setTicketId(resultSet.getInt(1));
                    optionalTicket = Optional.of(ticket);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return optionalTicket;
    }

    public List<Ticket> getTicketsBySessionId(int id) {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement pr =
                     cn.prepareStatement("select * from ticket where session_id = ?")) {
            pr.setInt(1, id);
            try (ResultSet resultSet = pr.executeQuery()) {
                while (resultSet.next()) {
                    tickets.add(new Ticket(resultSet.getInt("id"),
                            resultSet.getInt("session_id"),
                            resultSet.getInt("pos_row"),
                            resultSet.getInt("cell"),
                            new User(resultSet.getInt("user_id"))));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }
}
