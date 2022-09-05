package ru.movietheater.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.movietheater.model.Session;
import ru.movietheater.model.Ticket;
import ru.movietheater.service.SessionService;
import ru.movietheater.service.TicketService;
import ru.movietheater.util.ColumnRowSeparator;

import java.util.Map;


@Controller
@ThreadSafe
public class TicketControl {

    private final SessionService sessionService;

    private final TicketService ticketService;

    public TicketControl(SessionService sessionService, TicketService ticketService) {
        this.sessionService = sessionService;
        this.ticketService = ticketService;
    }

    @GetMapping("/addTicketForm")
    public String addTicketForm(@RequestParam("colRow") String input,
                                @RequestParam("sessionId") int sessionId,
                                Model model) {
        Map<String, String> stringMap = ColumnRowSeparator.getColRow(input);
        model.addAttribute("column" ,stringMap.get("column"));
        model.addAttribute("row" ,stringMap.get("row"));
        model.addAttribute("thisSession", sessionService.getSessionById(sessionId));
        return "createTicket";
    }

    @PostMapping("/createTicket")
    public String createTicket(@RequestParam("input") String a) {

        return "createTicket";
    }
}
