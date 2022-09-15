package ru.movietheater.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.movietheater.model.Ticket;
import ru.movietheater.model.User;
import ru.movietheater.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

@Controller
public class UserControl {
    private final UserService userService;

    public UserControl(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createOrGetUser")
    public String createOrGetUser(@ModelAttribute User user,
                                  @ModelAttribute Ticket ticket,
                                  Model model, HttpServletRequest request, RedirectAttributes redirect) {
        Optional<User> optionalUser;
        try {
            optionalUser = userService.addUser(user);
        } catch (SQLIntegrityConstraintViolationException e) {
            optionalUser = userService.getUserByAttr(user);
        }
        if (optionalUser.isEmpty()) {
            model.addAttribute("errorMessage", "Ошибка добавления пользователя");
            return "failAddTicketForm";
        }
        redirect.addAttribute("userId", optionalUser.get().getUserId());
        request.setAttribute(
                View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
        return "redirect:/createTicket";
    }
}
