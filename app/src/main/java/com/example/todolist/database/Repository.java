package com.example.todolist.database;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import com.example.todolist.entity.ToDo;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private final TodoDao todoDao;
    private final ExecutorService executorService;
    private final Handler handler;

    public Repository(Application application){
        TodoDatabase todoDatabase = TodoDatabase.getInstance(application);

        todoDao = todoDatabase.getTodoDao();

        executorService = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());
    }

    public void insert(ToDo toDo){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                todoDao.insert(toDo);
            }
        });
    }

    public void delete(ToDo toDo){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                todoDao.delete(toDo);
            }
        });
    }

    public void update(ToDo toDo){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                todoDao.update(toDo);
            }
        });
    }

    public LiveData<List<ToDo>> getAll(){
        return todoDao.getAll();
    }

    public LiveData<List<ToDo>> getByDate(String date){
        return todoDao.getByDate(date);
    }
}
