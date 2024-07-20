package com.example.todolist.clickHandlers;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.todolist.MainActivity;
import com.example.todolist.database.MyViewModel;
import com.example.todolist.entity.ToDo;

public class CRUDClickHandler {
    MyViewModel viewModel;
    ToDo todo;

    public CRUDClickHandler(MyViewModel viewModel, ToDo todo) {
        this.viewModel = viewModel;
        this.todo = todo;
    }

    public void onSaveClicked(View view){

        if(this.todo.getTitle() == null){
            Toast.makeText(view.getContext(), "Title shouldn't be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if(this.todo.getDate() == null){
            Toast.makeText(view.getContext(), "Date shouldn't be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        String title = this.todo.getTitle();
        String note;
        String date = this.todo.getDate();
        String time;
        boolean done = false;

        note = (this.todo.getNote() == null) ? "" : todo.getNote();
        time = (this.todo.getTime() == null) ? "" : todo.getTime();

        this.todo.setTitle(title);
        this.todo.setNote(note);
        this.todo.setDate(date);
        this.todo.setTime(time);
        this.todo.setDone(done);

        this.viewModel.insertTodo(this.todo);

        Intent intent = new Intent(view.getContext(), MainActivity.class);

        Toast.makeText(view.getContext(), "Task successfully added", Toast.LENGTH_SHORT).show();

        view.getContext().startActivity(intent);
    }

    public void onUpdateClicked(View view){
        if(this.todo.getTitle() == null || this.todo.getTitle().isEmpty()){
            Toast.makeText(view.getContext(), "Title shouldn't be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if(this.todo.getDate() == null || this.todo.getDate().isEmpty()){
            Toast.makeText(view.getContext(), "Date shouldn't be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        String title = this.todo.getTitle();
        String note;
        String date = this.todo.getDate();
        String time;
        boolean done = this.todo.getDone();

        note = (this.todo.getNote() == null || this.todo.getNote().isEmpty()) ? "" : todo.getNote();
        time = (this.todo.getTime() == null || this.todo.getTime().isEmpty()) ? "" : todo.getTime();

        this.todo.setTitle(title);
        this.todo.setNote(note);
        this.todo.setDate(date);
        this.todo.setTime(time);
        this.todo.setDone(done);

        this.viewModel.updateTodo(this.todo);

        Intent intent = new Intent(view.getContext(), MainActivity.class);

        Toast.makeText(view.getContext(), "Task successfully updated", Toast.LENGTH_SHORT).show();

        view.getContext().startActivity(intent);
    }
}
