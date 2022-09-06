package ru.movietheater.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.movietheater.model.Ticket;
import ru.movietheater.persistence.TicketDBStore;

import java.sql.Array;
import java.util.List;
import java.util.Optional;


@Service
@ThreadSafe
public class TicketService {
    private final TicketDBStore store;

    public TicketService(TicketDBStore store) {
        this.store = store;
    }

    public Optional<Ticket> addTicket(Ticket ticket) {
        return store.addTicket(ticket);
    }

    public List<Ticket> getTicketsBySessionId(int sessionId) {
        return store.getTicketsBySessionId(sessionId);
    }

}
