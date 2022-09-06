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
import ru.movietheater.service.UserService;
import ru.movietheater.util.ColumnRowSeparator;

import java.util.Map;
import java.util.Optional;


@Controller
@ThreadSafe
public class TicketControl {

    private final SessionService sessionService;

    private final TicketService ticketService;

    private final UserService userService;

    public TicketControl(SessionService sessionService, TicketService ticketService, UserService userService) {
        this.sessionService = sessionService;
        this.ticketService = ticketService;
        this.userService = userService;
    }

    @GetMapping("/addTicketForm")
    public String addTicketForm(@RequestParam("colRow") String input,
                                @RequestParam("sessionId") int sessionId,
                                Model model) {
        Map<String, String> stringMap = ColumnRowSeparator.getColRow(input);
        model.addAttribute("column" ,stringMap.get("column"));
        model.addAttribute("row" ,stringMap.get("row"));
        model.addAttribute("sess", sessionService.getSessionById(sessionId).get());
        userService.addUser(new User("a", "a", "b", "b"));
        userService.addUser(new User("a", "a", "b", "b"));
        return "addTicketForm";
    }

    @PostMapping("/createTicket")
    public String createTicket(@ModelAttribute User user,
                               @ModelAttribute Ticket ticket,
                               Model model) {
        Optional<User> optionalUser = userService.addUser(user);
        if (optionalUser.isEmpty()) {
            model.addAttribute("errorMessage", "Ошибка добавления пользователя");
            return "failAddTicketForm";
        }
        ticket.setUser(optionalUser.get());
        Optional<Ticket> optionalTicket = ticketService.addTicket(ticket);
        if (optionalTicket.isEmpty()) {
            model.addAttribute("errorMessage", "К сожалению выбранные места уже заняты");
            return "failAddTicketForm";
        }
        model.addAttribute("ticket", optionalTicket.get());
        model.addAttribute("user", optionalTicket.get().getUser());
        return "successAddTicket";
    }
}
