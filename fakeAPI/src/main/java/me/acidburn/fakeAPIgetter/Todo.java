package me.acidburn.fakeAPIgetter;

public class Todo {
    private int userId;
    private int id;
    private String title;
    private boolean completed;

    public String getUserId() {
        Integer i = userId;
        return i.toString();
    }

    public String getId() {
        Integer i = id;
        return i.toString();
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }



    public Todo(int userId, int id, String title, boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }
    public Todo() {
        this.userId = 0;
        this.id = 0;
        this.title = "";
        this.completed = false;
    }


}
