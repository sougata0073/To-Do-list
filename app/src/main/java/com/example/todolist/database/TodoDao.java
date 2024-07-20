package com.example.todolist.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todolist.entity.ToDo;

import java.util.List;

@Dao
public interface TodoDao {
    @Insert
    void insert(ToDo todo);

    @Delete
    void delete(ToDo toDo);

    @Update
    void update(ToDo toDo);

    @Query("SELECT * FROM todo_data")
    LiveData<List<ToDo>> getAll();

    @Query("SELECT * FROM todo_data WHERE todo_date = :date")
    LiveData<List<ToDo>> getByDate(String date);
}
