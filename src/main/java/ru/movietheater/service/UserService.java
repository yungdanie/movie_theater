package ru.movietheater.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.movietheater.model.User;
import ru.movietheater.persistence.UserDBStore;

import java.util.Optional;


@Service
@ThreadSafe
public class UserService {
    private final UserDBStore store;

    public UserService(UserDBStore store) {
        this.store = store;
    }

    public Optional<User> addUser(User user) {
        return store.addUser(user);
    }

}
