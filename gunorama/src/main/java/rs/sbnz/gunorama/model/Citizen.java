package rs.sbnz.gunorama.model;


import java.time.LocalDateTime;

public class Citizen {

    private int id;

    public Citizen() {

    }

    public Citizen(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
