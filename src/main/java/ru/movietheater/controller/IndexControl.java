package ru.movietheater.controller;


import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.movietheater.model.Session;
import ru.movietheater.service.SessionService;

import java.util.List;

@Controller
@ThreadSafe
public class IndexControl {

    private final SessionService sessionService;

    public IndexControl(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/index")
    public String index(Model model) {
        List<Session> sessionList = sessionService.getActualSessions();
        model.addAttribute("sessions", sessionList);
        return "index";
    }

}
