package ru.movietheater.model;

public class Ticket {
    private int ticketId;
    private int sessionId;
    private int column;
    private int row;
    private User user;

    public Ticket() {
    }

    public Ticket(int ticketId, int sessionId, int column, int row, User user) {
        this.ticketId = ticketId;
        this.sessionId = sessionId;
        this.column = column;
        this.row = row;
        this.user = user;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
