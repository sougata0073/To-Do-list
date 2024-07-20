package com.example.todolist.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todolist.entity.ToDo;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    private Repository repository;

    public MyViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
    }

    public void insertTodo(ToDo toDo){
        repository.insert(toDo);
    }

    public void deleteTodo(ToDo toDo){
        repository.delete(toDo);
    }

    public void updateTodo(ToDo toDo){
        repository.update(toDo);
    }

    public LiveData<List<ToDo>> getAllTodo(){
        return repository.getAll();
    }

    public LiveData<List<ToDo>> getTodoByDate(String date){
        return repository.getByDate(date);
    }
}
