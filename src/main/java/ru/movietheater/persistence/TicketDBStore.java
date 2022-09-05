package ru.movietheater.persistence;

import net.jcip.annotations.ThreadSafe;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Repository;
import ru.movietheater.model.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@ThreadSafe
public class TicketDBStore {

    private final BasicDataSource pool;
    private final UserDBStore userDBStore;

    public TicketDBStore(BasicDataSource pool, UserDBStore userDBStore) {
        this.pool = pool;
        this.userDBStore = userDBStore;
    }

    public List<Ticket> getAllTickets(Array ticketsID) {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement pr = cn.prepareStatement("select * from ticket where ticket_id = ANY(?::INT[])")) {
            pr.setArray(1, ticketsID);
            try (ResultSet resultSet = pr.executeQuery()) {
                while (resultSet.next()) {
                    tickets.add(new Ticket(resultSet.getInt("ticket_id"),
                            resultSet.getInt("session_id"),
                            resultSet.getInt("pos_row"),
                            resultSet.getInt("cell"),
                            userDBStore.getUserById(resultSet.getInt("user_id"))));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    public Ticket addTicket(Ticket ticket) {
        try (Connection cn = pool.getConnection();
             PreparedStatement pr =
                     cn.prepareStatement
                             ("insert into ticket(session_id, pos_row, cell, user_id) values (?, ?, ?, ?)")) {
            try (ResultSet resultSet = pr.getGeneratedKeys()) {
                if (resultSet.next()) {
                    ticket.setTicketId(resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticket;
    }
}
