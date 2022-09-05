package ru.movietheater.controller;


import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.movietheater.model.Hall;
import ru.movietheater.model.Session;
import ru.movietheater.model.Ticket;
import ru.movietheater.model.User;
import ru.movietheater.service.SessionService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@ThreadSafe
public class IndexControl {

    private final SessionService sessionService;

    public IndexControl(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("sessions", sessionService.getActualSessions());
        return "index";
    }

}
