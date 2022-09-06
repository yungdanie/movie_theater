package ru.movietheater.controller;


import net.jcip.annotations.ThreadSafe;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
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
        List<Session> sessionList = sessionService.getActualSessions();
        model.addAttribute("sessions", sessionList);
        return "index";
    }

}
