package ru.movietheater.controller;


import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.movietheater.model.Hall;
import ru.movietheater.model.Session;
import ru.movietheater.model.Ticket;
import ru.movietheater.model.User;
import ru.movietheater.service.SessionService;
import ru.movietheater.service.TicketService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@ThreadSafe
public class IndexControl {

    private final SessionService sessionService;
    private final TicketService ticketService;

    public IndexControl(SessionService sessionService, TicketService ticketService) {
        this.sessionService = sessionService;
        this.ticketService = ticketService;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("sessions", sessionService.getActualSessions());
        return "index";
    }

    @GetMapping("/calcPlace/{sessionId}/{column}/{row}")
    public String placeCalc(@PathVariable("column") int column,
                             @PathVariable("row") int row,
                             @PathVariable("sessionId") int sessionId) {
        List<Ticket> ticketList = ticketService.getTicketsBySessionId(sessionId);
        boolean bool = ticketList.stream().anyMatch(x -> x.getColumn() == column && x.getRow() == row);
        return bool ? "true" : "false";
    }

}
