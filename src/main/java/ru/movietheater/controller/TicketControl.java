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
import ru.movietheater.model.User;
import ru.movietheater.service.SessionService;
import ru.movietheater.service.TicketService;
import ru.movietheater.util.ColumnRowSeparator;

import java.util.Map;
import java.util.Optional;
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
        model.addAttribute("column", stringMap.get("column"));
        model.addAttribute("row", stringMap.get("row"));
        Optional<Session> optionalSession = sessionService.getSessionById(sessionId);
        if (optionalSession.isEmpty()) {
            model.addAttribute("errorMessage", "Выбранный сеанс отсутствует");
            return "failAction";
        }
        model.addAttribute("sess", optionalSession.get());
        return "addTicketForm";
    }

    @PostMapping("/createTicket")
    public String createTicket(Model model, @ModelAttribute User user,
                               @ModelAttribute Ticket ticket) {
        ticket.setUser(user);
        Optional<Ticket> optionalTicket = ticketService.addTicket(ticket);
        if (optionalTicket.isEmpty()) {
            model.addAttribute("errorMessage", "К сожалению выбранные места уже заняты");
            return "failAddTicketForm";
        }
        Optional<Session> optionalSession = sessionService.getSessionById(ticket.getSessionId());
        if (optionalSession.isEmpty()) {
            model.addAttribute("errorMessage", "Выбранный киносеанс отсутствует");
            return "failAddTicketForm";
        }
        model.addAttribute("thisSession", optionalSession.get());
        model.addAttribute("ticket", optionalTicket.get());
        model.addAttribute("user", optionalTicket.get().getUser());
        return "successAddTicket";
    }
}
