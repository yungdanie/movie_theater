package ru.movietheater.persistence;

import net.jcip.annotations.ThreadSafe;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Repository;
import ru.movietheater.model.Hall;

@Repository
@ThreadSafe
public class HallDBStore {
    private final BasicDataSource pool;

    public HallDBStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public Hall getHallById(int id) {
        return new Hall(1, "Main hall", 10, 10);
    }
}
