package ru.movietheater.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.movietheater.model.Session;
import ru.movietheater.persistence.SessionDBStore;

import java.util.List;
import java.util.Optional;

@Service
@ThreadSafe
public class SessionService {
    private final SessionDBStore store;

    public SessionService(SessionDBStore store) {
        this.store = store;
    }

    public Optional<Session> getSessionById(int id) {
        return store.getSessionById(id);
    }

    public List<Session> getActualSessions() {
        return store.getActualSessions();
    }
}
