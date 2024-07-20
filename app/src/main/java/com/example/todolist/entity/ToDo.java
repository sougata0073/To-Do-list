package com.example.todolist.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "todo_data")
public class ToDo implements Serializable {
    @ColumnInfo(name = "todo_id")
    @PrimaryKey(autoGenerate = true)
    private int todoId;
    @ColumnInfo(name = "todo_title")
    private String title;
    @ColumnInfo(name = "todo_note")
    private String note;
    @ColumnInfo(name = "todo_date")
    private String date;
    @ColumnInfo(name = "todo_time")
    private String time;
    @ColumnInfo(name = "todo_done")
    private boolean done;

    public ToDo(){

    }

    public ToDo(String title, String note, String date, String time, boolean done) {
        this.title = title;
        this.note = note;
        this.date = date;
        this.time = time;
        this.done = done;
    }

    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean getDone() {
        return this.done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
