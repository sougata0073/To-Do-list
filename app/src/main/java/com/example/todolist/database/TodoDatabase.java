package com.example.todolist.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.todolist.entity.ToDo;

@Database(entities = {ToDo.class}, version = 1)
public abstract class TodoDatabase extends RoomDatabase {
    public abstract TodoDao getTodoDao();

    private static TodoDatabase databaseInstance;

    public static synchronized TodoDatabase getInstance(Context context){
        if(databaseInstance == null){
            databaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                    TodoDatabase.class,
                    "todo_data").fallbackToDestructiveMigration().build();
        }
        return databaseInstance;
    }
}
