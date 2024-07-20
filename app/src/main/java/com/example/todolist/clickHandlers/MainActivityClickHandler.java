package com.example.todolist.clickHandlers;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.todolist.subActivity.AddNewTodoActivity;

public class MainActivityClickHandler {

    public MainActivityClickHandler() {

    }

    public void onFabClicked(View view){
        Context context = view.getContext();
        Intent intent = new Intent(context, AddNewTodoActivity.class);
        context.startActivity(intent);
    }
}
