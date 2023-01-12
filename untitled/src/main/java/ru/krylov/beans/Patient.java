package ru.krylov.beans;

public class Patient {
    private Integer id;
    private int snilsNum;
    private String name;
    private int polisId;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPolisId(int polisId) {
        this.polisId = polisId;
    }

    public void setSnilsNum(int snilsNum) {
        this.snilsNum = snilsNum;
    }

    public int getPolisId() {
        return polisId;
    }

    public int getSnilsNum() {
        return snilsNum;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return ("Id - " + getId() + " | " + "Name - " + getName() + " | " + "Snils num - " + getSnilsNum() + " | "
                + "Polis id - " + getPolisId());
    }
}
