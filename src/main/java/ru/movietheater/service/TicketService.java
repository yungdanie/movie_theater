package ru.movietheater.service;

import org.springframework.stereotype.Service;
import ru.movietheater.model.Ticket;
import ru.movietheater.persistence.TicketDBStore;

import java.sql.Array;
import java.util.List;


@Service
public class TicketService {
    private final TicketDBStore store;

    public TicketService(TicketDBStore store) {
        this.store = store;
    }

    public Ticket addTicket(Ticket ticket) {
        return store.addTicket(ticket);
    }
}
