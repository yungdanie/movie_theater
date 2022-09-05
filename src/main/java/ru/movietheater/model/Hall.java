package ru.movietheater.model;

public class Hall {
    private int id;
    private String name;
    private int column;
    private int row;

    public Hall() {
    }

    public Hall(String name, int column, int row) {
        this.name = name;
        this.column = column;
        this.row = row;
    }

    public Hall(int id, String name, int column, int row) {
        this.id = id;
        this.name = name;
        this.column = column;
        this.row = row;
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
}
