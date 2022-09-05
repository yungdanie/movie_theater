package ru.movietheater.model;

import java.time.LocalDateTime;
import java.util.List;

public class Session {
    private int id;
    private String name;
    private String description;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Hall hall;

    private List<Ticket> tickets;

    public Session() {
    }

    public Session(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Session(int id, String name, String description, LocalDateTime startTime, LocalDateTime endTime, Hall hall, List<Ticket> tickets) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.hall = hall;
        this.tickets = tickets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
