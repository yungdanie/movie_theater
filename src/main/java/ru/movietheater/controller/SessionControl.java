package ru.movietheater.controller;


import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.movietheater.model.Ticket;
import ru.movietheater.service.TicketService;

import java.util.List;

@Controller
@ThreadSafe
public class SessionControl {

    private final TicketService ticketService;

    public SessionControl(TicketService ticketService) {
        this.ticketService = ticketService;
    }


}
