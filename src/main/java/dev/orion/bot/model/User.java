package dev.orion.bot.model;

public class User {

    private long id;

    private String name;

    public User(String name) {
        super();
        this.name = name;
    }

    public User() {
        super();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

}